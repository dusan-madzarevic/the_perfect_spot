package com.ftn.uns.ac.rs.theperfectmeal.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ingredient")
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "recipe_id", nullable = false)
	private long recipeId;

	@Column(name = "recipe_name", nullable = false)
	private String recipeName;

	@Column(name = "servings", nullable = false)
	private int servings;

	@Column(name = "preparation_duration", nullable = false)
	private int prepDuration;

	@Column(name = "steps_number", nullable = false)
	private int stepsNumber;

	@Column(name = "steps_text", nullable = false)
	private String stepsText;

	@Column(name = "image")
	private byte[] image;

	@OneToMany(cascade = {CascadeType.ALL}, mappedBy = "recipe")
	private List<RecipeGrade> recipeGrades;
	
	public Recipe() {
		super();
	}

	public Recipe(long recipeId, String recipeName, int servings, int prepDuration, int stepsNumber, String stepsText,
			byte[] image) {
		super();
		this.recipeId = recipeId;
		this.recipeName = recipeName;
		this.servings = servings;
		this.prepDuration = prepDuration;
		this.stepsNumber = stepsNumber;
		this.stepsText = stepsText;
		this.image = image;
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

}
