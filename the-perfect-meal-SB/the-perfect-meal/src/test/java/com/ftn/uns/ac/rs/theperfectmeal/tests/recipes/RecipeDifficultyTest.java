package com.ftn.uns.ac.rs.theperfectmeal.tests.recipes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.ftn.uns.ac.rs.theperfectmeal.dto.RecipeCalcInfo;
import com.ftn.uns.ac.rs.theperfectmeal.dto.RecipeRequirements;
import com.ftn.uns.ac.rs.theperfectmeal.model.Ingredient;
import com.ftn.uns.ac.rs.theperfectmeal.model.Recipe;
import com.ftn.uns.ac.rs.theperfectmeal.model.RecipeGrade;
import com.ftn.uns.ac.rs.theperfectmeal.model.RecipeIngredient;
import com.ftn.uns.ac.rs.theperfectmeal.model.RecipeIngredientKey;
import com.ftn.uns.ac.rs.theperfectmeal.util.CaloricType;
import com.google.protobuf.TextFormat.ParseException;

public class RecipeDifficultyTest {

	private KieSession kieSession;
	@BeforeEach
	public void setUp() throws ParseException {
			

		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("com.ftn.uns.ac.rs", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
		kieSession = kContainer.newKieSession("thePerfectMealSession");
		
		Ingredient ing1 = new Ingredient(1, "Carrot", 50, null);
		Ingredient ing2 = new Ingredient(2, "Potato", 70, null);
		Ingredient ing3 = new Ingredient(3, "Fatty meat", 300, null);
		
		Recipe r1 = new Recipe(1, "Soup", 4, 1800, 5, "asdf", null);
		Recipe r2 = new Recipe(2, "FattySoup", 4, 3600, 5, "asdf", null);
		
		RecipeIngredient ring1 = new RecipeIngredient(new RecipeIngredientKey(1, 1), r1, ing1, 100);
		RecipeIngredient ring2 = new RecipeIngredient(new RecipeIngredientKey(1, 2), r1, ing2, 150);
		
		RecipeIngredient ring21 = new RecipeIngredient(new RecipeIngredientKey(2, 1), r2, ing1, 150);
		RecipeIngredient ring22 = new RecipeIngredient(new RecipeIngredientKey(2, 2), r2, ing2, 350);
		RecipeIngredient ring23 = new RecipeIngredient(new RecipeIngredientKey(2, 3), r2, ing3, 500);
		
		ArrayList<RecipeIngredient> ingredients = new ArrayList<RecipeIngredient>();
		ingredients.add(ring1);
		ingredients.add(ring2);
		
		ArrayList<RecipeIngredient> ingredients2 = new ArrayList<RecipeIngredient>();
		ingredients2.add(ring21);
		ingredients2.add(ring22);
		ingredients2.add(ring23);
		
		r1.setIngredients(ingredients);
		r2.setIngredients(ingredients2);
		
		RecipeRequirements recReq = new RecipeRequirements();
		recReq.setLowCalorie(true);
		
		this.kieSession.insert(recReq);
		
		List<Recipe> bestGraded = new ArrayList<>();

		kieSession.setGlobal("bestGraded", bestGraded);
		
	    List<Recipe> bestGradedLastMonth = new  ArrayList<>(); 
	    kieSession.setGlobal("bestGradedLastMonth", bestGradedLastMonth);
		
	    RecipeGrade grade1 = new RecipeGrade(r1);
	    grade1.setDate(Date.from(Instant.now()));
	    grade1.setId(1);
	    grade1.setValue(5);
	    
	    List<RecipeGrade> recipeGrades = new ArrayList<RecipeGrade>();
	    
	    recipeGrades.add(grade1);
	    
	    r1.setRecipeGrades(recipeGrades);
	    
	    this.kieSession.insert(r1);
	    
	    RecipeGrade grade2 = new RecipeGrade(r1);
	    grade2.setDate(Date.from(Instant.now()));
	    grade2.setId(2);
	    grade2.setValue(5);
	    
	    List<RecipeGrade> recipeGrades2 = new ArrayList<RecipeGrade>();
	    
	    recipeGrades2.add(grade2);
	    
	    r2.setRecipeGrades(recipeGrades2);
	    
	    this.kieSession.insert(r2);
	}
	
	
	@Test
	public void testLowDifficulty() {
		
		RecipeCalcInfo info = new RecipeCalcInfo();
		info.setRecipeId(1L);
		
		this.kieSession.insert(info);
		kieSession.getAgenda().getAgendaGroup("ingredients").setFocus();
		this.kieSession.fireAllRules(1);
		
		assertEquals(info.getCaloricType(), CaloricType.LOW_CAL);
		
	}
	
	@Test
	public void testOvertime() {
		
		
		
	}
	
	@Test
	public void testLowCalorie() {
		
		RecipeCalcInfo info = new RecipeCalcInfo();
		info.setRecipeId(1L);
		
		this.kieSession.insert(info);
		kieSession.getAgenda().getAgendaGroup("recipe-difficulty").setFocus();
		this.kieSession.fireAllRules(1);
		
		assertEquals(info.getCaloricType(), CaloricType.LOW_CAL);
		
	}
	
	@Test
	public void testMediumDifficulty() {
		
		RecipeCalcInfo info = new RecipeCalcInfo();
		info.setRecipeId(2L);
		
		this.kieSession.insert(info);
		kieSession.getAgenda().getAgendaGroup("recipe-difficulty").setFocus();
		this.kieSession.fireAllRules(1);
		
		assertEquals(info.getCaloricType(), CaloricType.HIGH_CAL);
		
	}
	
	@Test
	public void testHighDifficulty() {
		
		RecipeCalcInfo info = new RecipeCalcInfo();
		info.setRecipeId(2L);
		
		this.kieSession.insert(info);
		kieSession.getAgenda().getAgendaGroup("recipe-difficulty").setFocus();
		this.kieSession.fireAllRules(1);
		
		assertEquals(info.getCaloricType(), CaloricType.HIGH_CAL);
		
	}
	
}
