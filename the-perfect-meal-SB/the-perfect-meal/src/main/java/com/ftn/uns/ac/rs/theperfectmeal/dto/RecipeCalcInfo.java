package com.ftn.uns.ac.rs.theperfectmeal.dto;

import com.ftn.uns.ac.rs.theperfectmeal.util.CaloricType;
import com.ftn.uns.ac.rs.theperfectmeal.util.CookingSkill;

public class RecipeCalcInfo {

	private long recipeId;

	private CaloricType caloricType;

	private CookingSkill recipeDifficulty;

	private boolean ingredientsMismatch;

	private int prepOvertime;

	public RecipeCalcInfo() {
		super();
	}

	public RecipeCalcInfo(long recipeId, CaloricType caloricType, CookingSkill recipeDifficulty,
			boolean ingredientsMismatch, int prepOvertime) {
		super();
		this.recipeId = recipeId;
		this.caloricType = caloricType;
		this.recipeDifficulty = recipeDifficulty;
		this.ingredientsMismatch = ingredientsMismatch;
		this.prepOvertime = prepOvertime;
	}

	public long getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(long recipeId) {
		this.recipeId = recipeId;
	}

	public CaloricType getCaloricType() {
		return caloricType;
	}

	public void setCaloricType(CaloricType caloricType) {
		this.caloricType = caloricType;
	}

	public CookingSkill getRecipeDifficulty() {
		return recipeDifficulty;
	}

	public void setRecipeDifficulty(CookingSkill recipeDifficulty) {
		this.recipeDifficulty = recipeDifficulty;
	}

	public boolean isIngredientsMismatch() {
		return ingredientsMismatch;
	}

	public void setIngredientsMismatch(boolean ingredientsMismatch) {
		this.ingredientsMismatch = ingredientsMismatch;
	}

	public int getPrepOvertime() {
		return prepOvertime;
	}

	public void setPrepOvertime(int prepOvertime) {
		this.prepOvertime = prepOvertime;
	}

}
