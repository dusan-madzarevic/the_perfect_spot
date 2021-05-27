package com.ftn.uns.ac.rs.theperfectmeal.dto;

import com.ftn.uns.ac.rs.theperfectmeal.util.RecipeType;

public class RecipeDTO {
	
	private long recipeId;

	private String recipeName;

	private int servings;

	private int prepDuration;

	private int stepsNumber;

	private String stepsText;

	private byte[] image;
	
	private RecipeType type;

	public RecipeDTO() {
		super();
	}

	public RecipeDTO(long recipeId, String recipeName, int servings, int prepDuration, int stepsNumber,
			String stepsText, byte[] image, RecipeType type) {
		super();
		this.recipeId = recipeId;
		this.recipeName = recipeName;
		this.servings = servings;
		this.prepDuration = prepDuration;
		this.stepsNumber = stepsNumber;
		this.stepsText = stepsText;
		this.image = image;
		this.type = type;
	}

	public long getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(long recipeId) {
		this.recipeId = recipeId;
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

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public RecipeType getType() {
		return type;
	}

	public void setType(RecipeType type) {
		this.type = type;
	}
	
	
}
