package com.ftn.uns.ac.rs.theperfectmeal.templates;

import java.util.ArrayList;

import com.ftn.uns.ac.rs.theperfectmeal.model.Cuisine;
import com.ftn.uns.ac.rs.theperfectmeal.model.Prices;

public class FilterByCuisinePricesTemplateModel {
	private ArrayList<Prices> prices;
	private ArrayList<Cuisine> cuisines;

	public FilterByCuisinePricesTemplateModel() {
		super();
	}

	public FilterByCuisinePricesTemplateModel(ArrayList<Prices> prices, ArrayList<Cuisine> cuisines) {
		super();
		this.prices = prices;
		this.cuisines = cuisines;
	}

	public ArrayList<Prices> getPrices() {
		return prices;
	}

	public void setPrices(ArrayList<Prices> prices) {
		this.prices = prices;
	}

	public ArrayList<Cuisine> getCuisines() {
		return cuisines;
	}

	public void setCuisines(ArrayList<Cuisine> cuisines) {
		this.cuisines = cuisines;
	}

}
