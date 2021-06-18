package com.ftn.uns.ac.rs.theperfectmeal.dto;

import com.ftn.uns.ac.rs.theperfectmeal.model.Restaurant;

public class RestaurantAvgGrade {

	private Restaurant restaurant;
	private double avgGrade;

	public RestaurantAvgGrade(Restaurant restaurant, double avgGrade) {
		super();
		this.restaurant = restaurant;
		this.avgGrade = avgGrade;
	}

	public RestaurantAvgGrade() {
		super();
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public double getAvgGrade() {
		return avgGrade;
	}

	public void setAvgGrade(double avgGrade) {
		this.avgGrade = avgGrade;
	}

}
