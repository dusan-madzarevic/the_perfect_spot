package com.ftn.uns.ac.rs.theperfectmeal.tests.restaurants;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.ftn.uns.ac.rs.theperfectmeal.dto.RestaurantRequirements;
import com.ftn.uns.ac.rs.theperfectmeal.model.Authority;
import com.ftn.uns.ac.rs.theperfectmeal.model.Cuisine;
import com.ftn.uns.ac.rs.theperfectmeal.model.MusicGenre;
import com.ftn.uns.ac.rs.theperfectmeal.model.Occassion;
import com.ftn.uns.ac.rs.theperfectmeal.model.Prices;
import com.ftn.uns.ac.rs.theperfectmeal.model.RegisteredUser;
import com.ftn.uns.ac.rs.theperfectmeal.model.Restaurant;
import com.ftn.uns.ac.rs.theperfectmeal.model.RestaurantGrade;

public class RestaurantRequirementsTest {
	
	private KieSession kieSession;
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private Restaurant rest1;
	private Restaurant rest2;
	
	@BeforeEach
	public void setUp() throws ParseException {

		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("com.ftn.uns.ac.rs", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
		kieSession = kContainer.newKieSession("thePerfectMealSession");
	
		
		//napraviti restorane i insertovati ih u sesiju
		rest1 = new Restaurant();
		rest2 = new Restaurant();
		
		rest1.setAccessForDisabled(true);
		rest1.setAddress("Kralja Petra I 23");
		rest1.setCuisine(Cuisine.SERBIAN);
		rest1.setDateOfLastGrade(sdf.parse("2021-05-23"));
		rest1.setPrices(Prices.CHEAP);
		rest1.setPhoneNumber("020294583");
		rest1.setHasBusinessHall(false);
		rest1.setHasCarPark(true);
		rest1.setHasGarden(true);
		rest1.setHasPlayground(false);
		rest1.setName("Olimp");
		rest1.setHasSmokingPart(true);
		rest1.setHasWifi(false);
		rest1.setLiveMusic(true);
		rest1.setId(1);
		rest1.setWebSite("www.ddd.com");
		rest1.setWorkingHoursStart("7:00");
		rest1.setWorkingHoursEnd("22:00");
		rest1.setMusicGenre(MusicGenre.PIANO);
		List<RestaurantGrade> grades1 = new ArrayList<RestaurantGrade>();
		RestaurantGrade g1 = new RestaurantGrade();
		RegisteredUser user1 = new RegisteredUser();
		List<Authority> authorities = new ArrayList<Authority>();
		Authority a = new Authority();
		a.setId(1L);
		a.setName("USER");
		authorities.add(a);
		user1.setAuthorities(authorities);
		user1.setEmail("user1@gmail.com");
		user1.setFirstName("Marko");
		user1.setLastName("Maric");
		user1.setId(1L);
		user1.setPassword("marko");
		user1.setLastLoginAttempt(0L);
		user1.setLoginCooldown(false);
		g1.setValue(5);
		g1.setDate(sdf.parse("2021-05-23"));
		g1.setUser(user1);
		g1.setId(1);
		grades1.add(g1);
		rest1.setGrades(grades1);
		
		
		
		rest2.setId(2);
		rest2.setName("Velvet");
		rest2.setAccessForDisabled(false);
		rest2.setAddress("Kumanovska 43");
		rest2.setCuisine(Cuisine.SERBIAN);
		rest2.setDateOfLastGrade(sdf.parse("2021-05-29"));
		rest2.setHasBusinessHall(false);
		rest2.setHasCarPark(false);
		rest2.setHasGarden(true);
		rest2.setHasPlayground(true);
		rest2.setHasSmokingPart(false);
		rest2.setHasWifi(true);
		rest2.setLiveMusic(true);
		rest2.setPrices(Prices.AFFORDABLE);
		rest2.setPhoneNumber("020294583");
		rest2.setMusicGenre(MusicGenre.TAMBOURINES);
		rest2.setWebSite("www.ddd.com");
		rest2.setWorkingHoursStart("7:00");
		rest2.setWorkingHoursEnd("22:00");
		List<RestaurantGrade> grades2 = new ArrayList<RestaurantGrade>();
		RestaurantGrade g2 = new RestaurantGrade();
		
		g2.setValue(5);
		g2.setDate(sdf.parse("2021-05-23"));
		g2.setUser(user1);
		g2.setId(1);
		grades2.add(g2);
		rest2.setGrades(grades2);
	
	}
	
	@Test
	public void setInputData() throws ParseException {	
		this.kieSession.insert(rest2);
		this.kieSession.insert(rest1);
		RestaurantRequirements req = new RestaurantRequirements(0, 0, Cuisine.ASIAN, false, Occassion.BUSINESS_MEAL, Prices.AFFORDABLE, false,false);
		this.kieSession.insert(req);
	
		this.kieSession.getAgenda().getAgendaGroup("fill-restaurant-requirements").setFocus();
		int firedRules = this.kieSession.fireAllRules();
		
		
		kieSession.getAgenda().getAgendaGroup("restaurant").setFocus();
		int dva =  kieSession.fireAllRules();
		Restaurant top = (Restaurant) kieSession.getGlobal("foundRestaurant");
		System.out.println(dva);
		
		assertEquals(4, firedRules);
		assertNotNull(top);
		
		
		
		
	}
}
