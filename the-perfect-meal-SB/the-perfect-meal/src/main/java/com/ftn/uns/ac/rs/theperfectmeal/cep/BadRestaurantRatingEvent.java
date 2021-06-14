package com.ftn.uns.ac.rs.theperfectmeal.cep;

import java.util.Date;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import com.ftn.uns.ac.rs.theperfectmeal.model.RestaurantGrade;

@Role(Role.Type.EVENT)
@Timestamp("timestamp")
@Expires("2h")
public class BadRestaurantRatingEvent {
	
	private RestaurantGrade grade;
	private Date timestamp;
	
	public BadRestaurantRatingEvent() {
		super();
	}

	public BadRestaurantRatingEvent(RestaurantGrade grade, Date timestamp) {
		super();
		this.grade = grade;
		this.timestamp = timestamp;
	}

	public RestaurantGrade getGrade() {
		return grade;
	}

	public void setGrade(RestaurantGrade grade) {
		this.grade = grade;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	

}
