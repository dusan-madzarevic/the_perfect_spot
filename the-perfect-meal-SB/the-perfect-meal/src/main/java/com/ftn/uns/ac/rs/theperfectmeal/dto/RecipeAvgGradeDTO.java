package com.ftn.uns.ac.rs.theperfectmeal.dto;

public class RecipeAvgGradeDTO {
	private RecipeDTO recipe;
	private double avgGrade;
	public RecipeAvgGradeDTO() {
		super();
	}
	public RecipeAvgGradeDTO(RecipeDTO recipe, double avgGrade) {
		super();
		this.recipe = recipe;
		this.avgGrade = avgGrade;
	}
	public RecipeDTO getRecipe() {
		return recipe;
	}
	public void setRecipe(RecipeDTO recipe) {
		this.recipe = recipe;
	}
	public double getAvgGrade() {
		return avgGrade;
	}
	public void setAvgGrade(double avgGrade) {
		this.avgGrade = avgGrade;
	}
	
	
}
