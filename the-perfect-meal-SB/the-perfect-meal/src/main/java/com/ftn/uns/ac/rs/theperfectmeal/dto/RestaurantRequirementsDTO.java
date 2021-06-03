 package com.ftn.uns.ac.rs.theperfectmeal.dto;

import java.util.Date;

import com.ftn.uns.ac.rs.theperfectmeal.model.Cousine;
import com.ftn.uns.ac.rs.theperfectmeal.model.Occassion;
import com.ftn.uns.ac.rs.theperfectmeal.model.Prices;

public class RestaurantRequirementsDTO {


	private double lon;
	private double lat;
    private Cousine cousine;
    private boolean petFriendly;
    private Occassion occassion;
    private Prices prices;
    private boolean goingByCar;
    private boolean accessForDisabled;
    private Date date;
    
	public RestaurantRequirementsDTO() {
		super();
	}



	public RestaurantRequirementsDTO(double lon, double lat, Cousine cousine, boolean petFriendly, Occassion occassion,
			Prices prices, boolean goingByCar, boolean accessForDisabled, Date date) {
		super();
		this.lon = lon;
		this.lat = lat;
		this.cousine = cousine;
		this.petFriendly = petFriendly;
		this.occassion = occassion;
		this.prices = prices;
		this.goingByCar = goingByCar;
		this.accessForDisabled = accessForDisabled;
		this.date = date;
	}



	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public Cousine getCousine() {
		return cousine;
	}

	public void setCousine(Cousine cousine) {
		this.cousine = cousine;
	}

	public boolean isPetFriendly() {
		return petFriendly;
	}

	public void setPetFriendly(boolean petFriendly) {
		this.petFriendly = petFriendly;
	}

	public Occassion getOccassion() {
		return occassion;
	}

	public void setOccassion(Occassion occassion) {
		this.occassion = occassion;
	}

	public Prices getPrices() {
		return prices;
	}

	public void setPrices(Prices prices) {
		this.prices = prices;
	}

	public boolean isGoingByCar() {
		return goingByCar;
	}

	public void setGoingByCar(boolean goingByCar) {
		this.goingByCar = goingByCar;
	}

	public boolean isAccessForDisabled() {
		return accessForDisabled;
	}

	public void setAccessForDisabled(boolean accessForDisabled) {
		this.accessForDisabled = accessForDisabled;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}
    
    
    
    
}
