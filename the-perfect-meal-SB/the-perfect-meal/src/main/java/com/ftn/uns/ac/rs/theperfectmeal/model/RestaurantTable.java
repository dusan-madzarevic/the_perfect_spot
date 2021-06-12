package com.ftn.uns.ac.rs.theperfectmeal.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class RestaurantTable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "table_id", nullable = false)
	private long id;
	
	@ManyToOne
	private Restaurant restaurant;
	
	@Column(name="table_num", nullable = false)
	private int tableNum;
	
	@Column(name = "num_of_seats", nullable = false)
	private int numOfSeats;
	
	@Enumerated(value = EnumType.STRING)
	private TableStatus status;
	
	@OneToMany(cascade = {CascadeType.ALL}, mappedBy = "table")
	private List<Reservation> reservations;
	
	public RestaurantTable() {
		super();
	}

	public RestaurantTable(long id, Restaurant restaurant, int tableNum, int numOfSeats, TableStatus status,
			List<Reservation> reservations) {
		super();
		this.id = id;
		this.restaurant = restaurant;
		this.tableNum = tableNum;
		this.numOfSeats = numOfSeats;
		this.status = status;
		this.reservations = reservations;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public int getTableNum() {
		return tableNum;
	}

	public void setTableNum(int tableNum) {
		this.tableNum = tableNum;
	}

	public int getNumOfSeats() {
		return numOfSeats;
	}

	public void setNumOfSeats(int numOfSeats) {
		this.numOfSeats = numOfSeats;
	}

	public TableStatus getStatus() {
		return status;
	}

	public void setStatus(TableStatus status) {
		this.status = status;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	
	
	
}
