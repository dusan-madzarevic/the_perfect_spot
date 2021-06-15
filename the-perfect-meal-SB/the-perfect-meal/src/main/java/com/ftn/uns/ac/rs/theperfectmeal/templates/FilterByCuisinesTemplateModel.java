package com.ftn.uns.ac.rs.theperfectmeal.templates;

import java.util.ArrayList;

import com.ftn.uns.ac.rs.theperfectmeal.model.Cuisine;

public class FilterByCuisinesTemplateModel {

	private ArrayList<Cuisine> cuisineTypes;

	public FilterByCuisinesTemplateModel() {
		super();
	}

	public FilterByCuisinesTemplateModel(ArrayList<Cuisine> cuisineTypes) {
		super();
		this.cuisineTypes = cuisineTypes;
	}

	public ArrayList<Cuisine> getCuisineTypes() {
		return cuisineTypes;
	}

	public void setCuisineTypes(ArrayList<Cuisine> cuisineTypes) {
		this.cuisineTypes = cuisineTypes;
	}

}
