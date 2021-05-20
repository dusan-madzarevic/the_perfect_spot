package com.ftn.uns.ac.rs.theperfectmeal.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "recipe_grade")
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
