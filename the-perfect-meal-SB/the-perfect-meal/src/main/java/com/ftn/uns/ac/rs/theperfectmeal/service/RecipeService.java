package com.ftn.uns.ac.rs.theperfectmeal.service;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.uns.ac.rs.theperfectmeal.dto.MessageResponse;
import com.ftn.uns.ac.rs.theperfectmeal.dto.RecipeRequirements;
import com.ftn.uns.ac.rs.theperfectmeal.model.Recipe;
import com.ftn.uns.ac.rs.theperfectmeal.repository.RecipeRepository;

@Service
public class RecipeService {

	@Autowired
	private KieStatefulSessionService kieService;
	
	@Autowired
	private RecipeRepository recipeRepository;
	
	public MessageResponse process(RecipeRequirements requirements) {
		
		this.kieService.releaseRulesSession();
		KieSession kieSession = kieService.getRulesSession();
		System.out.println(requirements.getServings());
		kieSession.insert(requirements);
		for (Recipe recipe : recipeRepository.findAll()) {
			
			kieSession.insert(recipe);
			
		}
		kieSession.getAgenda().getAgendaGroup("recipe").setFocus();
		kieSession.fireAllRules();
		kieSession.dispose();
		return new MessageResponse("Successfully");

		
	}
	
}
