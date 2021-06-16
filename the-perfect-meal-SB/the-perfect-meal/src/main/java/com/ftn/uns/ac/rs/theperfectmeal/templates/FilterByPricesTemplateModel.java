package com.ftn.uns.ac.rs.theperfectmeal.templates;

import java.util.ArrayList;

import com.ftn.uns.ac.rs.theperfectmeal.model.Prices;

public class FilterByPricesTemplateModel {

	private ArrayList<Prices> prices;

	public FilterByPricesTemplateModel() {
		super();
	}

	public FilterByPricesTemplateModel(ArrayList<Prices> prices) {
		super();
		this.prices = prices;
	}

	public ArrayList<Prices> getPrices() {
		return prices;
	}

	public void setPrices(ArrayList<Prices> prices) {
		this.prices = prices;
	}

}
