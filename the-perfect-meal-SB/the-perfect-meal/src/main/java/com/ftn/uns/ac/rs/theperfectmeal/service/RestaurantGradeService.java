package com.ftn.uns.ac.rs.theperfectmeal.service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.uns.ac.rs.theperfectmeal.cep.BadRestaurantRatingEvent;
import com.ftn.uns.ac.rs.theperfectmeal.dto.RestaurantGradeDto;
import com.ftn.uns.ac.rs.theperfectmeal.model.Alarm;
import com.ftn.uns.ac.rs.theperfectmeal.model.AlarmType;
import com.ftn.uns.ac.rs.theperfectmeal.model.RegisteredUser;
import com.ftn.uns.ac.rs.theperfectmeal.model.Restaurant;
import com.ftn.uns.ac.rs.theperfectmeal.model.RestaurantGrade;
import com.ftn.uns.ac.rs.theperfectmeal.repository.RegisteredUserRepository;
import com.ftn.uns.ac.rs.theperfectmeal.repository.RestaurantGradeRepository;
import com.ftn.uns.ac.rs.theperfectmeal.repository.RestaurantRepository;
import com.ftn.uns.ac.rs.theperfectmeal.util.BadRatingAlarm;

@Service
public class RestaurantGradeService {

	@Autowired
	private RestaurantGradeRepository gradeRepository;
	
	@Autowired
	private RegisteredUserRepository userRepository;
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	KieStatefulSessionService kieSessionService;
	
	@Autowired
	private AlarmService alarmService;
	
	public int getByRestaurantAndUser(long restId, String email) {

		RegisteredUser loggedUser = this.userRepository.findByEmail(email);
		RestaurantGrade grade = this.gradeRepository.getByRestaurantAndUser(restId, loggedUser.getId());
		if (grade == null) {
			return 0;
		}
		return grade.getValue();
	}

	public boolean save(RestaurantGradeDto dto) {
		RegisteredUser loggedUser = this.userRepository.findByEmail(dto.email);
		long userId = loggedUser.getId();
		//check if user has already rated restaurant
		RestaurantGrade rg = this.gradeRepository.findByUserIdAndRestaurantId(userId,dto.getRestId());
		if (rg != null) {
			//already graded, edit it
			rg.setDate(LocalDate.now());
			rg.setValue(dto.getGrade());
			this.gradeRepository.save(rg);
			
			BadRestaurantRatingEvent event = new BadRestaurantRatingEvent(rg ,Date.from(Instant.now()));
			KieSession kieSession = kieSessionService.getEventsSession();
			kieSession.getAgenda().getAgendaGroup("bad-restaurant-rating").setFocus();
			BadRatingAlarm ratingAlarm = new BadRatingAlarm();
			kieSession.setGlobal("badRatingAlarm", ratingAlarm);
			kieSession.insert(event);
			kieSession.fireAllRules();	
			
			if(ratingAlarm.getRestaurantId() == dto.getRestId()) {
				
				Alarm alarm = new Alarm(String.format("The rating of restaurant %s fell below 2.3", rg.getRestaurant().getName()), AlarmType.RESTAURANT_BAD_RATING);
				this.alarmService.save(alarm);
				
			}
			
			return true;
		}
		
		//not graded yet, add grade to database
		RestaurantGrade gr = new RestaurantGrade();
		Restaurant restaurant = this.restaurantRepository.findById(dto.getRestId()).orElse(null);
		List<RestaurantGrade> grades = restaurant.getGrades();
		gr.setDate(LocalDate.now());
		gr.setUser(loggedUser);
		gr.setId(44);
		gr.setRestaurant(restaurant);
		gr.setValue(dto.getGrade());
		grades.add(gr);
		//this.gradeRepository.save(gr);
		this.restaurantRepository.save(restaurant);
		
		BadRestaurantRatingEvent event = new BadRestaurantRatingEvent(gr ,Date.from(Instant.now()));
		KieSession kieSession = kieSessionService.getEventsSession();
		kieSession.getAgenda().getAgendaGroup("bad-restaurant-rating").setFocus();
		BadRatingAlarm ratingAlarm = new BadRatingAlarm();
		kieSession.setGlobal("badRatingAlarm", ratingAlarm);
		kieSession.insert(event);
		kieSession.fireAllRules();
		
		if(ratingAlarm.getRestaurantId() == dto.getRestId()) {
			
			Alarm alarm = new Alarm(String.format("The rating of restaurant %s fell below 2.3", restaurant.getName()), AlarmType.RESTAURANT_BAD_RATING);
			this.alarmService.save(alarm);
			
		}
		
		return true;		
	}
}
