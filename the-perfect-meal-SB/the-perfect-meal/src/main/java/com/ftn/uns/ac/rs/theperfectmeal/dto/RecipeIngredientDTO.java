package com.ftn.uns.ac.rs.theperfectmeal.dto;

public class RecipeIngredientDTO {

	private long id;

	private String name;

	private long amount;

	private double calories;

	public RecipeIngredientDTO() {
		super();
	}

	public RecipeIngredientDTO(long id, String name, long amount, double calories) {
		super();
		this.id = id;
		this.name = name;
		this.amount = amount;
		this.calories = calories;
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

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public double getCalories() {
		return calories;
	}

	public void setCalories(double calories) {
		this.calories = calories;
	}

	
	
}
