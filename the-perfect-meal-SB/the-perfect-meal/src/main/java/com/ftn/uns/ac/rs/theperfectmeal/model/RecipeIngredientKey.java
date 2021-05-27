package com.ftn.uns.ac.rs.theperfectmeal.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RecipeIngredientKey implements Serializable {

	@Column(name = "recipe_id")
	private long recipeId;
	
	@Column(name = "ingredient_id")
	private long ingredientId;

	public RecipeIngredientKey() {
		super();
	}

	public RecipeIngredientKey(long recipeId, long ingredientId) {
		super();
		this.recipeId = recipeId;
		this.ingredientId = ingredientId;
	}

	public long getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(long recipeId) {
		this.recipeId = recipeId;
	}

	public long getIngredientId() {
		return ingredientId;
	}

	public void setIngredientId(long ingredientId) {
		this.ingredientId = ingredientId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(recipeId, ingredientId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RecipeIngredientKey other = (RecipeIngredientKey) obj;
		if (ingredientId != other.ingredientId)
			return false;
		if (recipeId != other.recipeId)
			return false;
		return true;
	}
	
	
	
}
