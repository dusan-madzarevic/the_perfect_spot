package com.ftn.uns.ac.rs.theperfectmeal.tests.recipes;

import org.junit.Before;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.google.protobuf.TextFormat.ParseException;

public class RecipeRequirementsTest {

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
	
	
	
}
