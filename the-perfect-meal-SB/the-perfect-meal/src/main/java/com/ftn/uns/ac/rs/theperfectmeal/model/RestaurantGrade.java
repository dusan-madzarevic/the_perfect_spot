package com.ftn.uns.ac.rs.theperfectmeal.model;


import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class RestaurantGrade extends Grade {
	
	@ManyToOne
	private Restaurant restaurant;

	public RestaurantGrade() {
		super();
	}

	public RestaurantGrade(Restaurant restaurant) {
		super();
		this.restaurant = restaurant;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
	
	
}
