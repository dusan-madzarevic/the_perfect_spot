package com.ftn.uns.ac.rs.theperfectmeal.util;

public class BadRatingAlarm {

	private long restaurantId;

	public BadRatingAlarm() {
		super();
	}

	public BadRatingAlarm(long restaurantId) {
		super();
		this.restaurantId = restaurantId;
	}

	public long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(long restaurantId) {
		this.restaurantId = restaurantId;
	}
	
	
}
