package com.ftn.uns.ac.rs.theperfectmeal.dto;

public class RestaurantAvgGradeDto {
	private RestaurantDTO restaurant;
	private double avgGrade;
	
	public RestaurantAvgGradeDto() {
		super();
	}


	public RestaurantAvgGradeDto(RestaurantDTO restaurant, double avgGrade) {
		super();
		this.restaurant = restaurant;
		this.avgGrade = avgGrade;
	}

	public RestaurantDTO getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(RestaurantDTO restaurant) {
		this.restaurant = restaurant;
	}

	public double getAvgGrade() {
		return avgGrade;
	}

	public void setAvgGrade(double avgGrade) {
		this.avgGrade = avgGrade;
	}
	

}
