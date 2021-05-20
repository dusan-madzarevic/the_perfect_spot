package com.ftn.uns.ac.rs.theperfectmeal.model;

import javax.persistence.ManyToOne;

public class RecipeGrade extends Grade {

	@ManyToOne
	private Recipe recipe;

	public RecipeGrade() {
		super();
	}

	public RecipeGrade(Recipe recipe ) {
		super();
		this.recipe  = recipe ;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}


	
}
