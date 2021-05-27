package com.ftn.uns.ac.rs.theperfectmeal.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "recipe_ingredient")
public class RecipeIngredient {

	@EmbeddedId
	private RecipeIngredientKey id;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("recipeId")
	private Recipe recipe;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("ingredientId")
	private Ingredient ingredient;

	@Column(name = "amount")
	private long amount;

	public RecipeIngredient() {
		super();
	}

	public RecipeIngredient(RecipeIngredientKey id, Recipe recipe, Ingredient ingredient, long amount) {
		super();
		this.id = id;
		this.recipe = recipe;
		this.ingredient = ingredient;
		this.amount = amount;
	}

	public RecipeIngredientKey getId() {
		return id;
	}

	public void setId(RecipeIngredientKey id) {
		this.id = id;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

}
