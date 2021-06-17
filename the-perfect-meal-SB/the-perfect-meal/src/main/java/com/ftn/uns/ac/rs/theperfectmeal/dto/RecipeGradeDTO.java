package com.ftn.uns.ac.rs.theperfectmeal.dto;

public class RecipeGradeDTO {

	private String email;
	private long recipeId;
	private int grade;

	public RecipeGradeDTO() {
		super();
	}

	public RecipeGradeDTO(String email, long recipeId, int grade) {
		super();
		this.email = email;
		this.recipeId = recipeId;
		this.grade = grade;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(long recipeId) {
		this.recipeId = recipeId;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

}
