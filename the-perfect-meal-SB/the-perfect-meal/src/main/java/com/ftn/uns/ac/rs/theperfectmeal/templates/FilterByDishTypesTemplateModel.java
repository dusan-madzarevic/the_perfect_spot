package com.ftn.uns.ac.rs.theperfectmeal.templates;

import java.util.ArrayList;

import com.ftn.uns.ac.rs.theperfectmeal.util.RecipeType;

public class FilterByDishTypesTemplateModel {

	private ArrayList<RecipeType> dishTypes;

	public FilterByDishTypesTemplateModel() {
		super();
	}

	public FilterByDishTypesTemplateModel(ArrayList<RecipeType> dishTypes) {
		super();
		this.dishTypes = dishTypes;
	}

	public ArrayList<RecipeType> getDishTypes() {
		return dishTypes;
	}

	public void setDishTypes(ArrayList<RecipeType> dishTypes) {
		this.dishTypes = dishTypes;
	}

}
