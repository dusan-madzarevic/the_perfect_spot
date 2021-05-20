package com.ftn.uns.ac.rs.theperfectmeal.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class RestaurantGrade extends Grade {
	
	@Column(name="restourant_id")
	private Restaurant restaurant;

	public RestaurantGrade() {
		super();
	}

	public RestaurantGrade(Restaurant restaurant ) {
		super();
		this.restaurant  = restaurant ;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
	
	
}
