package com.ftn.uns.ac.rs.theperfectmeal.service;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.uns.ac.rs.theperfectmeal.dto.MessageResponse;
import com.ftn.uns.ac.rs.theperfectmeal.dto.RecipeDTO;
import com.ftn.uns.ac.rs.theperfectmeal.dto.RecipeRequirements;
import com.ftn.uns.ac.rs.theperfectmeal.helper.RecipeMapper;
import com.ftn.uns.ac.rs.theperfectmeal.model.Recipe;
import com.ftn.uns.ac.rs.theperfectmeal.repository.RecipeRepository;

@Service
public class RecipeService {

	@Autowired
	private KieStatefulSessionService kieService;
	
	@Autowired
	private RecipeRepository recipeRepository;
	
	@Autowired
	private RecipeMapper recipeMapper;
	
	public MessageResponse process(RecipeRequirements requirements) {
		
		this.kieService.releaseRulesSession();
		KieSession kieSession = kieService.getRulesSession();

		kieSession.insert(requirements);
		for (Recipe recipe : recipeRepository.findAll()) {
			
			kieSession.insert(recipe);
			
		}
		kieSession.getAgenda().getAgendaGroup("recipe").setFocus();
		kieSession.fireAllRules();
		
		kieSession.getAgenda().getAgendaGroup("Calculating recipe score").setFocus();
		kieSession.fireAllRules();
		
		Recipe topRecipe = (Recipe) kieSession.getGlobal("topRecipe");
		System.out.println(topRecipe.getRecipeName());
		
		kieSession.dispose();
		return new MessageResponse("Successfully");

		
	}
	
	public ArrayList<RecipeDTO> bestGradedRecipe() {
		
		this.kieService.releaseRulesSession();
		KieSession session = this.kieService.getRulesSession();

		List<Recipe> bestGraded = new ArrayList<>();

		session.setGlobal("bestGraded", bestGraded);

		List<Recipe> recipes = this.recipeRepository.findAll();

		for (Recipe r : recipes) {
			session.insert(r);
		}

		session.getAgenda().getAgendaGroup("best-graded-report").setFocus();
		session.fireAllRules();
		ArrayList<RecipeDTO> recipesDto = new ArrayList<RecipeDTO>();
		for(Recipe r: bestGraded) {
			recipesDto.add(this.recipeMapper.toDto(r));
		}
		
		session.getAgenda().getAgendaGroup("last-month-best-graded-report").setFocus();
		session.fireAllRules();
		List<Recipe> bestGradedLastMonth = new ArrayList<>();
		session.setGlobal("bestGradedLastMonth", bestGradedLastMonth);
		for(Recipe r: bestGradedLastMonth)
			System.out.println(r.getRecipeName());
		return recipesDto;
	}
	
}
