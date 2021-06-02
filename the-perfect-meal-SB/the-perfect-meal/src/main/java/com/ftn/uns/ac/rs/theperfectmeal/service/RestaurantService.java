package com.ftn.uns.ac.rs.theperfectmeal.service;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.uns.ac.rs.theperfectmeal.dto.MessageResponse;
import com.ftn.uns.ac.rs.theperfectmeal.dto.RestaurantDTO;
import com.ftn.uns.ac.rs.theperfectmeal.dto.RestaurantRequirementsDTO;
import com.ftn.uns.ac.rs.theperfectmeal.helper.RestaurantMapper;
import com.ftn.uns.ac.rs.theperfectmeal.model.Recipe;
import com.ftn.uns.ac.rs.theperfectmeal.model.Restaurant;
import com.ftn.uns.ac.rs.theperfectmeal.repository.RestaurantRepository;

@Service
public class RestaurantService {

	@Autowired
	private KieStatefulSessionService kieService;
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private RestaurantMapper restaurantMapper;
	
	public MessageResponse process(RestaurantRequirementsDTO requirements) {
		this.kieService.releaseRulesSession();
		KieSession kieSession = kieService.getRulesSession();

		kieSession.insert(requirements);
		for (Restaurant rest : restaurantRepository.findAll()) {
			
			kieSession.insert(rest);
			
		}
		kieSession.getAgenda().getAgendaGroup("restaurant").setFocus();
		kieSession.fireAllRules();
		
//		kieSession.getAgenda().getAgendaGroup("Calculating restaurant score").setFocus();
//		kieSession.fireAllRules();
//		
//		Restaurant topRestaurant = (Restaurant) kieSession.getGlobal("topRestaurant");
//		System.out.println(topRestaurant.getName());
		
		kieSession.dispose();
		return new MessageResponse("Successfully");

	}
	
	public ArrayList<RestaurantDTO> bestGradeRestaurant(){
		this.kieService.releaseRulesSession();
		KieSession session = this.kieService.getRulesSession();
		
		List<Restaurant> best = new ArrayList<Restaurant>();
		
		session.setGlobal("bestGraded", best);
		
		List<Restaurant> restaurants = this.restaurantRepository.findAll();
		
		for (Restaurant r: restaurants) {
			session.insert(r);
		}

		session.getAgenda().getAgendaGroup("best-graded-report").setFocus();
		session.fireAllRules();
		
		ArrayList<RestaurantDTO> restorantsDTO = new ArrayList<RestaurantDTO>();
		for(Restaurant r: best) {
			restorantsDTO.add(this.restaurantMapper.toDto(r));
		}
		return restorantsDTO;
		
	}
}
