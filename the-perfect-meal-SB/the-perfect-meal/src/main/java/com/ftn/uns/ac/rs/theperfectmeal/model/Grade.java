package com.ftn.uns.ac.rs.theperfectmeal.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
@MappedSuperclass
public abstract class Grade {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "grade_id")
	private Long id;
	
	@Column
	private int value;
	
	@Column
	private LocalDate date;
	
	@ManyToOne
	private RegisteredUser user;
	
	public Grade() {
		super();
	}

	public Grade(int value, LocalDate date, RegisteredUser user, long id) {
		super();
		this.value = value;
		this.date = date;
		this.user= user;
		this.id = id;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public RegisteredUser getUser() {
		return user;
	}

	public void setUser(RegisteredUser user) {
		this.user = user;
	}
	
	
	
}
