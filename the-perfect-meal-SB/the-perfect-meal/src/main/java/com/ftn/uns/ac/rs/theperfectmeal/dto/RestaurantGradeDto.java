package com.ftn.uns.ac.rs.theperfectmeal.dto;

public class RestaurantGradeDto {

	public String email;
	public long restId;
	public int grade;

	public RestaurantGradeDto() {
		super();
	}

	public RestaurantGradeDto(String email, long restId, int grade) {
		super();
		this.email = email;
		this.restId = restId;
		this.grade = grade;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getRestId() {
		return restId;
	}

	public void setRestId(long restId) {
		this.restId = restId;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

}
