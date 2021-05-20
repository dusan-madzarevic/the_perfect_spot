package com.ftn.uns.ac.rs.theperfectmeal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Restaurant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "restaurant_id")
	private long id;
	
	@Column
	private String name;
	
	@Column
	private double grade;
	
	@Column
	private String address;
	
	@Column(name = "date_of_last_grade")
	private long dateOfLastGrade;
	
	@Column(name="web_site")
	private String webSite;
	
	private String cousine;
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	@Column(name="working_hours")
	private String workingHours;
	
	@Column(name="garden")
	private boolean hasGarden;
	
	@Column(name="playground")
	private boolean hasPlayground;
	
	@Column(name="car_park")
	private boolean hasCarPark;
	
	@Column(name="smoking_part")
	private boolean hasSmokingPart;
	
	@Column
	private byte[] image;
	
	public Restaurant() {
		super();
	}

	public Restaurant(long id, String name, double grade, String address, long dateOfLastGrade, String webSite,
			String cousine, String phoneNumber, String workingHours, boolean hasGarden, boolean hasPlayground,
			boolean hasCarPark, boolean hasSmokingPart, byte[] image) {
		super();
		this.id = id;
		this.name = name;
		this.grade = grade;
		this.address = address;
		this.dateOfLastGrade = dateOfLastGrade;
		this.webSite = webSite;
		this.cousine = cousine;
		this.phoneNumber = phoneNumber;
		this.workingHours = workingHours;
		this.hasGarden = hasGarden;
		this.hasPlayground = hasPlayground;
		this.hasCarPark = hasCarPark;
		this.hasSmokingPart = hasSmokingPart;
		this.image = image;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getDateOfLastGrade() {
		return dateOfLastGrade;
	}

	public void setDateOfLastGrade(long dateOfLastGrade) {
		this.dateOfLastGrade = dateOfLastGrade;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public String getCousine() {
		return cousine;
	}

	public void setCousine(String cousine) {
		this.cousine = cousine;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(String workingHours) {
		this.workingHours = workingHours;
	}

	public boolean isHasGarden() {
		return hasGarden;
	}

	public void setHasGarden(boolean hasGarden) {
		this.hasGarden = hasGarden;
	}

	public boolean isHasPlayground() {
		return hasPlayground;
	}

	public void setHasPlayground(boolean hasPlayground) {
		this.hasPlayground = hasPlayground;
	}

	public boolean isHasCarPark() {
		return hasCarPark;
	}

	public void setHasCarPark(boolean hasCarPark) {
		this.hasCarPark = hasCarPark;
	}

	public boolean isHasSmokingPart() {
		return hasSmokingPart;
	}

	public void setHasSmokingPart(boolean hasSmokingPart) {
		this.hasSmokingPart = hasSmokingPart;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	
	
	

}
