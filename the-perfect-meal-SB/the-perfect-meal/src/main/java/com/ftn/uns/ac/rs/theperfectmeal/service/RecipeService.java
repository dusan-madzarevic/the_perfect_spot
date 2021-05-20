package com.ftn.uns.ac.rs.theperfectmeal.service;

import org.kie.api.runtime.KieContainer;
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
	private KieContainer kieContainer;
	
	@Autowired
	private RecipeRepository recipeRepository;
	
	public MessageResponse process(RecipeRequirements requirements) {
		
		System.out.println(requirements.getServings());
		System.out.println(requirements.getSkill().toString());
		System.out.println(requirements.getType().toString());
		
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.insert(requirements);
		for (Recipe recipe : recipeRepository.findAll()) {
			
			kieSession.insert(recipe);
			
		}
		kieSession.fireAllRules();
		kieSession.dispose();
		return new MessageResponse("Successfully");
		
	}
	
}
