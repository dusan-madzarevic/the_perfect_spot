package com.ftn.uns.ac.rs.theperfectmeal.tests.restaurants;


import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.ftn.uns.ac.rs.theperfectmeal.dto.RestaurantRequirements;
import com.google.protobuf.TextFormat.ParseException;

public class RestaurantRequirementsTest {
	
	private KieSession kieSession;
	
	
	@Before
	public void setUp() throws ParseException {

		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("com.ftn.uns.ac.rs", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
		kieSession = kContainer.newKieSession("TPMSession");
		kieSession.getAgenda().getAgendaGroup("restaurant-requirements").setFocus();
		
		//napraviti restorane i insertovati ih u sesiju
		
		//this.kieSession.insert(rest);
	}
	
	@Test
	public void setInputData() throws ParseException {		
		//RestaurantRequirements req = new RestaurantRequirements(lon, lat, cousine, petFriendly, occassion, prices, goingByCar, date, accessForDisabled, servingAlcohol, liveMusic, musicGenre, hasBusinessHall, hasWifi, hasCarPark, hasSmokingPart, hasPlayground, hasGarden);
		//this.kieSession.insert(req);
		
	}
}
