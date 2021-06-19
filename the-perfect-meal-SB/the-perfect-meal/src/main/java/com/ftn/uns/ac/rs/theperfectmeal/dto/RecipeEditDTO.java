package com.ftn.uns.ac.rs.theperfectmeal.dto;

import java.util.List;

public class RecipeEditDTO {

	private long recipeId;
	private String recipeName;
	private int servings;
	private int prepDuration;
	private int stepsNumber;
	private String stepsText;
	private String type;
	private byte[] image;
	private List<IngredientAmount> ingredients;

	public RecipeEditDTO() {
		super();
	}

	public RecipeEditDTO(long recipeId, String recipeName, int servings, int prepDuration, int stepsNumber,
			String stepsText, String type, byte[] image, List<IngredientAmount> ingredients) {
		super();
		this.recipeId = recipeId;
		this.recipeName = recipeName;
		this.servings = servings;
		this.prepDuration = prepDuration;
		this.stepsNumber = stepsNumber;
		this.stepsText = stepsText;
		this.type = type;
		this.image = image;
		this.ingredients = ingredients;
	}

	public String getRecipeName() {
		return recipeName;
	}

	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	public int getServings() {
		return servings;
	}

	public void setServings(int servings) {
		this.servings = servings;
	}

	public int getPrepDuration() {
		return prepDuration;
	}

	public void setPrepDuration(int prepDuration) {
		this.prepDuration = prepDuration;
	}

	public int getStepsNumber() {
		return stepsNumber;
	}

	public void setStepsNumber(int stepsNumber) {
		this.stepsNumber = stepsNumber;
	}

	public String getStepsText() {
		return stepsText;
	}

	public void setStepsText(String stepsText) {
		this.stepsText = stepsText;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public List<IngredientAmount> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<IngredientAmount> ingredients) {
		this.ingredients = ingredients;
	}

	public long getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(long recipeId) {
		this.recipeId = recipeId;
	}

}
