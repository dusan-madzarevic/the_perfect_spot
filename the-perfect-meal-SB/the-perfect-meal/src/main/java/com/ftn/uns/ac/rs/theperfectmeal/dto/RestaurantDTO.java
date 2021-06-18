package com.ftn.uns.ac.rs.theperfectmeal.dto;

import com.ftn.uns.ac.rs.theperfectmeal.model.Cuisine;
import com.ftn.uns.ac.rs.theperfectmeal.model.MusicGenre;
import com.ftn.uns.ac.rs.theperfectmeal.model.Prices;

public class RestaurantDTO {
	
	private long id;
	private String name;
	private String webSite;
	private String workingHours;
	private String description;
	private Cuisine cuisine;
	private double grade;
	private Prices prices;
	private String phone;
	//objediniti u klasu Lokacija?
	private String address;
	private double lat;
	private double lon;
	private byte[] image;
	private MusicGenre musicGenre;
	private boolean hasGarden;
	private boolean hasWifi;
	private boolean hasBusinessHall;
	private boolean hasLiveMusic;
	private boolean hasCarPark;
	private boolean hasPlayground;
	private boolean isPetFriendly;
	private boolean isServingAlcohol;
	private boolean hasSmokingPart;
	private boolean hasAccessForDisabled;
	private int recommendationCount;
	
	public RestaurantDTO() {
		super();
	}

	

	public RestaurantDTO(long id, String name, String webSite, String workingHours, String description, Cuisine cuisine,
			double grade, Prices prices, String phone, String address, double lat, double lon, byte[] image,
			MusicGenre musicGenre, boolean hasGarden, boolean hasWifi, boolean hasBusinessHall, boolean hasLiveMusic,
			boolean hasCarPark, boolean hasPlayground, boolean isPetFriendly, boolean isServingAlcohol,
			boolean hasSmokingPart, boolean hasAccessForDisabled, int recommendationCount) {
		super();
		this.id = id;
		this.name = name;
		this.webSite = webSite;
		this.workingHours = workingHours;
		this.description = description;
		this.cuisine = cuisine;
		this.grade = grade;
		this.prices = prices;
		this.phone = phone;
		this.address = address;
		this.lat = lat;
		this.lon = lon;
		this.image = image;
		this.musicGenre = musicGenre;
		this.hasGarden = hasGarden;
		this.hasWifi = hasWifi;
		this.hasBusinessHall = hasBusinessHall;
		this.hasLiveMusic = hasLiveMusic;
		this.hasCarPark = hasCarPark;
		this.hasPlayground = hasPlayground;
		this.isPetFriendly = isPetFriendly;
		this.isServingAlcohol = isServingAlcohol;
		this.hasSmokingPart = hasSmokingPart;
		this.hasAccessForDisabled = hasAccessForDisabled;
		this.recommendationCount = recommendationCount;
	}



	public int getRecommendationCount() {
		return recommendationCount;
	}



	public void setRecommendationCount(int recommendationCount) {
		this.recommendationCount = recommendationCount;
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

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public String getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(String workingHours) {
		this.workingHours = workingHours;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Cuisine getCuisine() {
		return cuisine;
	}

	public void setCuisine(Cuisine cuisine) {
		this.cuisine = cuisine;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public Prices getPrices() {
		return prices;
	}

	public void setPrices(Prices prices) {
		this.prices = prices;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public MusicGenre getMusicGenre() {
		return musicGenre;
	}

	public void setMusicGenre(MusicGenre musicGenre) {
		this.musicGenre = musicGenre;
	}

	public boolean isHasGarden() {
		return hasGarden;
	}

	public void setHasGarden(boolean hasGarden) {
		this.hasGarden = hasGarden;
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

	public boolean isHasLiveMusic() {
		return hasLiveMusic;
	}

	public void setHasLiveMusic(boolean hasLiveMusic) {
		this.hasLiveMusic = hasLiveMusic;
	}

	public boolean isHasCarPark() {
		return hasCarPark;
	}

	public void setHasCarPark(boolean hasCarPark) {
		this.hasCarPark = hasCarPark;
	}

	public boolean isHasPlayground() {
		return hasPlayground;
	}

	public void setHasPlayground(boolean hasPlayground) {
		this.hasPlayground = hasPlayground;
	}

	public boolean isPetFriendly() {
		return isPetFriendly;
	}

	public void setPetFriendly(boolean isPetFriendly) {
		this.isPetFriendly = isPetFriendly;
	}

	public boolean isServingAlcohol() {
		return isServingAlcohol;
	}

	public void setServingAlcohol(boolean isServingAlcohol) {
		this.isServingAlcohol = isServingAlcohol;
	}

	public boolean isHasSmokingPart() {
		return hasSmokingPart;
	}

	public void setHasSmokingPart(boolean hasSmokingPart) {
		this.hasSmokingPart = hasSmokingPart;
	}

	public boolean isHasAccessForDisabled() {
		return hasAccessForDisabled;
	}

	public void setHasAccessForDisabled(boolean hasAccessForDisabled) {
		this.hasAccessForDisabled = hasAccessForDisabled;
	}

	
	

}
