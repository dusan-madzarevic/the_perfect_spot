package com.ftn.uns.ac.rs.theperfectmeal.dto;

import java.util.List;

public class RecipeDTO {

	private long id;

	private String name;

	private int servings;

	private int prepDuration;

	private int stepsNumber;

	private String stepsText;

	private byte[] image;

	private String type;

	private List<RecipeIngredientDTO> ingredients;

	private double grade;

	public RecipeDTO() {
		super();
	}

	public RecipeDTO(long id, String name, int servings, int prepDuration, int stepsNumber, String stepsText,
			byte[] image, String type, List<RecipeIngredientDTO> ingredients, double grade) {
		super();
		this.id = id;
		this.name = name;
		this.servings = servings;
		this.prepDuration = prepDuration;
		this.stepsNumber = stepsNumber;
		this.stepsText = stepsText;
		this.image = image;
		this.type = type;
		this.ingredients = ingredients;
		this.grade = grade;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<RecipeIngredientDTO> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<RecipeIngredientDTO> ingredients) {
		this.ingredients = ingredients;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

}
