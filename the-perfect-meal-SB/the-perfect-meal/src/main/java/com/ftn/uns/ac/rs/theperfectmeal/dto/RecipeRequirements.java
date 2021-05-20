package com.ftn.uns.ac.rs.theperfectmeal.dto;

import java.util.List;

import com.ftn.uns.ac.rs.theperfectmeal.util.CookingSkill;
import com.ftn.uns.ac.rs.theperfectmeal.util.RecipeType;

public class RecipeRequirements {

	private CookingSkill skill;
	
	private boolean lowCalorie;
	
	private int servings;
	
	private List<IngredientAmount> ingredients;
	
	private boolean limitedTime;
	
	private RecipeType type;

	public RecipeRequirements() {
		super();
	}

	public RecipeRequirements(CookingSkill skill, boolean lowCalorie, int servings, List<IngredientAmount> ingredients,
			boolean limitedTime, RecipeType type) {
		super();
		this.skill = skill;
		this.lowCalorie = lowCalorie;
		this.servings = servings;
		this.ingredients = ingredients;
		this.limitedTime = limitedTime;
		this.type = type;
	}

	public CookingSkill getSkill() {
		return skill;
	}

	public void setSkill(CookingSkill skill) {
		this.skill = skill;
	}

	public boolean isLowCalorie() {
		return lowCalorie;
	}

	public void setLowCalorie(boolean lowCalorie) {
		this.lowCalorie = lowCalorie;
	}

	public int getServings() {
		return servings;
	}

	public void setServings(int servings) {
		this.servings = servings;
	}

	public List<IngredientAmount> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<IngredientAmount> ingredients) {
		this.ingredients = ingredients;
	}

	public boolean isLimitedTime() {
		return limitedTime;
	}

	public void setLimitedTime(boolean limitedTime) {
		this.limitedTime = limitedTime;
	}

	public RecipeType getType() {
		return type;
	}

	public void setType(RecipeType type) {
		this.type = type;
	}
	
	
	
}
