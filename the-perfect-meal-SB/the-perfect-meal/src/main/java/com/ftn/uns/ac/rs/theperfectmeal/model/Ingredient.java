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
public class Ingredient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ingredient_id", nullable = false)
	private long ingredientId;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "caloric_value", nullable = false)
	private double caloricValue;

	@Column(name = "image")
	private byte[] image;
	
	@OneToMany(cascade = {CascadeType.ALL}, mappedBy = "ingredient", orphanRemoval = true)
	private List<RecipeIngredient> recipes;

	public Ingredient() {
		super();
	}

	public Ingredient(long ingredientId, String name, double caloricValue, byte[] image) {
		super();
		this.ingredientId = ingredientId;
		this.name = name;
		this.caloricValue = caloricValue;
		this.image = image;
	}

	public long getIngredientId() {
		return ingredientId;
	}

	public void setIngredientId(long ingredientId) {
		this.ingredientId = ingredientId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCaloricValue() {
		return caloricValue;
	}

	public void setCaloricValue(double caloricValue) {
		this.caloricValue = caloricValue;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public List<RecipeIngredient> getRecipes() {
		return recipes;
	}

	public void setRecipes(List<RecipeIngredient> recipes) {
		this.recipes = recipes;
	}

}
