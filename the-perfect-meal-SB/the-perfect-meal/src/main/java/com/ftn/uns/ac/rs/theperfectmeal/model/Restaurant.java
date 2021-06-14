package com.ftn.uns.ac.rs.theperfectmeal.model;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import java.lang.Math;

@Entity
public class Restaurant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "restaurant_id")
	private long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String description;
	
	@OneToMany(cascade = {CascadeType.ALL}, mappedBy = "restaurant")
	private List<RestaurantGrade> grades;
	
	@OneToMany(cascade = {CascadeType.ALL}, mappedBy = "restaurant")
	private List<RestaurantTable> tables;
	
	@OneToMany(cascade = {CascadeType.ALL}, mappedBy = "restaurant")
	private List<Reservation> reservations;
	
	@Column(nullable = false)
	private String address;
	
	@Column(name = "date_of_last_grade", nullable = false)
	private Date dateOfLastGrade;
	
	@Column(name="web_site")
	private String webSite;
	
	@Enumerated(value = EnumType.STRING)
	private Cuisine cuisine;
	
	@Enumerated(value = EnumType.STRING)
	private Prices prices;
	
	@Column(name="phone_number", nullable = false)
	private String phoneNumber;
	
	@Column(name="working_hours_start", nullable = false)
	private String workingHoursStart;
	
	@Column(name="working_hours_end", nullable = false)
	private String workingHoursEnd;
	
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
	
	@Column(name="petFriendly", nullable = false)
	private boolean petFriendly;
	
	@Column(name="accessForDisabled", nullable = false)
	private boolean accessForDisabled;
	
	@Column(name="lon", nullable = true)
	private double lon;
	
	@Column(name="lat", nullable = true)
	private double lat;
	
	
	public Restaurant() {
		super();
	}

	
	


	public Restaurant(long id, String name, String description, List<RestaurantGrade> grades,
			List<RestaurantTable> tables, List<Reservation> reservations, String address, Date dateOfLastGrade,
			String webSite, Cuisine cuisine, Prices prices, String phoneNumber, String workingHoursStart,String workingHoursEnd, boolean hasGarden,
			boolean hasPlayground, boolean hasCarPark, boolean hasSmokingPart, byte[] image, MusicGenre musicGenre,
			boolean liveMusic, boolean servingAlcohol, boolean hasWifi, boolean hasBusinessHall, boolean petFriendly,
			boolean accessForDisabled, double lon, double lat) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.grades = grades;
		this.tables = tables;
		this.reservations = reservations;
		this.address = address;
		this.dateOfLastGrade = dateOfLastGrade;
		this.webSite = webSite;
		this.cuisine = cuisine;
		this.prices = prices;
		this.phoneNumber = phoneNumber;
		this.workingHoursStart = workingHoursStart;
		this.workingHoursEnd = workingHoursEnd;
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
		this.petFriendly = petFriendly;
		this.accessForDisabled = accessForDisabled;
		this.lon = lon;
		this.lat = lat;
	}





	public Restaurant(long id, String name, List<RestaurantGrade> grades, String address, Date dateOfLastGrade,
			String webSite, Cuisine cousine, Prices prices, String phoneNumber, String workingHoursStart,String workingHoursEnd, boolean hasGarden,
			boolean hasPlayground, boolean hasCarPark, boolean hasSmokingPart, byte[] image, MusicGenre musicGenre,
			boolean liveMusic, boolean servingAlcohol, boolean hasWifi, boolean hasBusinessHall, boolean petFriendly,
			boolean accessForDisabled, double lon, double lat) {
		super();
		this.id = id;
		this.name = name;
		this.grades = grades;
		this.address = address;
		this.dateOfLastGrade = dateOfLastGrade;
		this.webSite = webSite;
		this.cuisine = cousine;
		this.prices = prices;
		this.phoneNumber = phoneNumber;
		this.workingHoursStart = workingHoursStart;
		this.workingHoursEnd = workingHoursEnd;
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
		this.petFriendly = petFriendly;
		this.accessForDisabled = accessForDisabled;
		this.lon = lon;
		this.lat = lat;
	}



	public Restaurant(long id, String name, List<RestaurantGrade> grades, String address, Date dateOfLastGrade,
			String webSite, Cuisine cousine, Prices prices, String phoneNumber, String workingHoursStart,String workingHoursEnd, boolean hasGarden,
			boolean hasPlayground, boolean hasCarPark, boolean hasSmokingPart, byte[] image, MusicGenre musicGenre,
			boolean liveMusic, boolean servingAlcohol, boolean hasWifi, boolean hasBusinessHall, boolean petFriendly,
			boolean accessForDisabled) {
		super();
		this.id = id;
		this.name = name;
		this.grades = grades;
		this.address = address;
		this.dateOfLastGrade = dateOfLastGrade;
		this.webSite = webSite;
		this.cuisine = cousine;
		this.prices = prices;
		this.phoneNumber = phoneNumber;
		this.workingHoursStart = workingHoursStart;
		this.workingHoursEnd = workingHoursEnd;
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
		this.petFriendly = petFriendly;
		this.accessForDisabled = accessForDisabled;
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


	public Cuisine getCuisine() {
		return cuisine;
	}

	public void setCuisine(Cuisine cuisine) {
		this.cuisine = cuisine;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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


	public boolean isPetFriendly() {
		return petFriendly;
	}

	public void setPetFriendly(boolean petFriendly) {
		this.petFriendly = petFriendly;
	}


	public boolean isAccessForDisabled() {
		return accessForDisabled;
	}


	public void setAccessForDisabled(boolean accessForDisabled) {
		this.accessForDisabled = accessForDisabled;
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
	

	public List<RestaurantTable> getTables() {
		return tables;
	}

	public void setTables(List<RestaurantTable> tables) {
		this.tables = tables;
	}
	
	

	public List<Reservation> getReservations() {
		return reservations;
	}



	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}



	public String getDescription() {
		return description;
	}





	public void setDescription(String description) {
		this.description = description;
	}





	public String getWorkingHoursStart() {
		return workingHoursStart;
	}





	public void setWorkingHoursStart(String workingHoursStart) {
		this.workingHoursStart = workingHoursStart;
	}





	public String getWorkingHoursEnd() {
		return workingHoursEnd;
	}





	public void setWorkingHoursEnd(String workingHoursEnd) {
		this.workingHoursEnd = workingHoursEnd;
	}





	public double getDistance(double lat1, double lon1) {
		//return this.distance(lat1, lon1, this.lat, this.lon);
		return this.haversine(lat1, lon1);
	}
	

	private double deg2rad(double deg) {
		  return (deg * Math.PI / 180.0);
	}
	
	private double rad2deg(double rad) {
		  return (rad * 180.0 / Math.PI);
	}
		
	private double distance(double lat1, double lon1, double lat2, double lon2) {
		  double theta = lon1 - lon2;
		  double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		  dist = Math.acos(dist);
		  dist = rad2deg(dist);
		  dist = dist * 60 * 1.1515 * 1.609344;
		  return (dist);
		}

	public double haversine(double lat, double lon)
		{
		// distance between latitudes and longitudes
		double dLat = Math.toRadians(this.lat - lat);
		double dLon = Math.toRadians(this.lon - lon);
		
		// apply formula
		double a = Math.pow(Math.sin(dLat / 2), 2) + Math.pow(Math.sin(dLon / 2), 2) * Math.cos(Math.toRadians(lat)) * Math.cos(Math.toRadians(this.lat));
		double rad = 6371;
		double c = 2 * Math.asin(Math.sqrt(a));
		return rad * c;
		}
	
	public Date convertToHoursStart() {
		Date dt = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt);
		c.add(Calendar.HOUR, Integer.valueOf(this.workingHoursStart.split(":")[0]));
		dt = c.getTime();
		return dt;
	}
	
	public Date convertToHoursEnd() {
		Date dt = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt);
		if(this.workingHoursEnd.split(":")[0].equalsIgnoreCase("00") || this.workingHoursEnd.split(":")[0].equalsIgnoreCase("01")) {
			c.add(Calendar.DATE, 1);
			c.add(Calendar.HOUR, Integer.valueOf(this.workingHoursEnd.split(":")[0]) );
		} else
			c.add(Calendar.HOUR, Integer.valueOf(this.workingHoursEnd.split(":")[0]));
		dt = c.getTime();
		return dt;
	}
}
