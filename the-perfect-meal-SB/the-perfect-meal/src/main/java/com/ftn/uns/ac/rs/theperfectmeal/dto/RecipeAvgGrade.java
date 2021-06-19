package com.ftn.uns.ac.rs.theperfectmeal.dto;

import com.ftn.uns.ac.rs.theperfectmeal.model.Recipe;

public class RecipeAvgGrade {

	private Recipe recipe;
	private double avgGrade;

	public RecipeAvgGrade(Recipe recipe, double avgGrade) {
		super();
		this.recipe = recipe;
		this.avgGrade = avgGrade;
	}

	public RecipeAvgGrade() {
		super();
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public double getAvgGrade() {
		return avgGrade;
	}

	public void setAvgGrade(double avgGrade) {
		this.avgGrade = avgGrade;
	}
	
}
