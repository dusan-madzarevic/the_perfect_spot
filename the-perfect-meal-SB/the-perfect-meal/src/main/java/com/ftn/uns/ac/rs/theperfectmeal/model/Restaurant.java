package com.ftn.uns.ac.rs.theperfectmeal.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Restaurant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "restaurant_id")
	private long id;
	
	@Column(nullable = false)
	private String name;
	
	@OneToMany(cascade = {CascadeType.ALL}, mappedBy = "restaurant")
	private List<RestaurantGrade> grades;
	
	@Column(nullable = false)
	private String address;
	
	@Column(name = "date_of_last_grade", nullable = false)
	private Date dateOfLastGrade;
	
	@Column(name="web_site")
	private String webSite;
	
	@Enumerated(value = EnumType.STRING)
	private Cousine cousine;
	
	@Enumerated(value = EnumType.STRING)
	private Prices prices;
	
	@Column(name="phone_number", nullable = false)
	private String phoneNumber;
	
	@Column(name="working_hours", nullable = false)
	private String workingHours;
	
	@Column(name="garden", nullable = false)
	private boolean hasGarden;
	
	@Column(name="playground", nullable = false)
	private boolean hasPlayground;
	
	@Column(name="car_park", nullable = false)
	private boolean hasCarPark;
	
	@Column(name="smoking_part", nullable = false)
	private boolean hasSmokingPart;
	
	@Column
	private byte[] image;
	
	@Enumerated(value=EnumType.STRING)
	private MusicGenre musicGenre;
	
	@Column(name = "live_music", nullable = false)
	private boolean liveMusic;

	@Column(name = "alcohol", nullable = false)
	private boolean servingAlcohol;
	
	@Column(name="wifi", nullable=false)
	private boolean hasWifi;
	
	@Column(name="businessHall", nullable = false)
	private boolean hasBusinessHall;
	
	
	public Restaurant() {
		super();
	}





	public Restaurant(long id, String name, List<RestaurantGrade> grades, String address, Date dateOfLastGrade,
			String webSite, Cousine cousine, Prices prices, String phoneNumber, String workingHours, boolean hasGarden,
			boolean hasPlayground, boolean hasCarPark, boolean hasSmokingPart, byte[] image, MusicGenre musicGenre,
			boolean liveMusic, boolean servingAlcohol, boolean hasWifi, boolean hasBusinessHall) {
		super();
		this.id = id;
		this.name = name;
		this.grades = grades;
		this.address = address;
		this.dateOfLastGrade = dateOfLastGrade;
		this.webSite = webSite;
		this.cousine = cousine;
		this.prices = prices;
		this.phoneNumber = phoneNumber;
		this.workingHours = workingHours;
		this.hasGarden = hasGarden;
		this.hasPlayground = hasPlayground;
		this.hasCarPark = hasCarPark;
		this.hasSmokingPart = hasSmokingPart;
		this.image = image;
		this.musicGenre = musicGenre;
		this.liveMusic = liveMusic;
		this.servingAlcohol = servingAlcohol;
		this.hasWifi = hasWifi;
		this.hasBusinessHall = hasBusinessHall;
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


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDateOfLastGrade() {
		return dateOfLastGrade;
	}

	public void setDateOfLastGrade(Date dateOfLastGrade) {
		this.dateOfLastGrade = dateOfLastGrade;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public Cousine getCousine() {
		return cousine;
	}

	public void setCousine(Cousine cousine) {
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



	public List<RestaurantGrade> getGrades() {
		return grades;
	}



	public void setGrades(List<RestaurantGrade> grades) {
		this.grades = grades;
	}



	public Prices getPrices() {
		return prices;
	}



	public void setPrices(Prices prices) {
		this.prices = prices;
	}



	public MusicGenre getMusicGenre() {
		return musicGenre;
	}



	public void setMusicGenre(MusicGenre musicGenre) {
		this.musicGenre = musicGenre;
	}



	public boolean isLiveMusic() {
		return liveMusic;
	}



	public void setLiveMusic(boolean liveMusic) {
		this.liveMusic = liveMusic;
	}



	public boolean isServingAlcohol() {
		return servingAlcohol;
	}



	public void setServingAlcohol(boolean servingAlcohol) {
		this.servingAlcohol = servingAlcohol;
	}





	public boolean isHasWifi() {
		return hasWifi;
	}





	public void setHasWifi(boolean hasWifi) {
		this.hasWifi = hasWifi;
	}





	public boolean isHasBusinessHall() {
		return hasBusinessHall;
	}





	public void setHasBusinessHall(boolean hasBusinessHall) {
		this.hasBusinessHall = hasBusinessHall;
	}
	
	
	

}
