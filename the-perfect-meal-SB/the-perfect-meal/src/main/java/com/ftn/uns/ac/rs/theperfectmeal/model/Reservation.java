package com.ftn.uns.ac.rs.theperfectmeal.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;




@Entity
public class Reservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reservation_id", nullable = false)
	private long id;
	
	@ManyToOne
	private RestaurantTable table;
	
	@ManyToOne
	private Restaurant restaurant;
	
	@Column(nullable = false)
	private long userId;
	
	@Column(nullable = false)
	private Date date; //date and time
	
	@Column(nullable = false)
	private boolean canceled;

	public Reservation() {
		super();
	}

	public Reservation(long id, RestaurantTable table, Restaurant restaurant, long userId, Date date, boolean canceled) {
		super();
		this.id = id;
		this.table = table;
		this.restaurant = restaurant;
		this.userId = userId;
		this.date = date;
		this.canceled = canceled;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public RestaurantTable getTable() {
		return table;
	}

	public void setTable(RestaurantTable table) {
		this.table = table;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isCanceled() {
		return canceled;
	}

	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}
	
	
}
