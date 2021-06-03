package com.ftn.uns.ac.rs.theperfectmeal.dto;

import java.util.Date;

import com.ftn.uns.ac.rs.theperfectmeal.model.Cousine;
import com.ftn.uns.ac.rs.theperfectmeal.model.MusicGenre;
import com.ftn.uns.ac.rs.theperfectmeal.model.Occassion;
import com.ftn.uns.ac.rs.theperfectmeal.model.Prices;

public class RestaurantRequirements {
	
	private double lon;
	private double lat;
    private Cousine cousine;
    private boolean petFriendly;
    private Occassion occassion; 
    private Prices prices;
    private boolean goingByCar;
    private Date date;
    private boolean accessForDisabled;
    
    private boolean servingAlcohol;
    private boolean liveMusic;
    private MusicGenre musicGenre;
    
	private boolean hasBusinessHall;
	private boolean hasWifi;
	
	private boolean hasCarPark;
	
	private boolean hasSmokingPart;
	private boolean hasPlayground;
	private boolean hasGarden;
	
	private boolean active;
	
	public RestaurantRequirements() {
		super();
	}

	


	

	public RestaurantRequirements(double lon, double lat, Cousine cousine, boolean petFriendly, Occassion occassion,
			Prices prices, boolean goingByCar, boolean accessForDisabled) {
		super();
		this.lon = lon;
		this.lat = lat;
		this.cousine = cousine;
		this.petFriendly = petFriendly;
		this.occassion = occassion;
		this.prices = prices;
		this.goingByCar = goingByCar;
		this.accessForDisabled = accessForDisabled;
	}






	public RestaurantRequirements(double lon, double lat, Cousine cousine, boolean petFriendly, Occassion occassion,
			Prices prices, boolean goingByCar, Date date, boolean accessForDisabled, boolean servingAlcohol,
			boolean liveMusic, MusicGenre musicGenre, boolean hasBusinessHall, boolean hasWifi, boolean hasCarPark,
			boolean hasSmokingPart, boolean hasPlayground, boolean hasGarden, boolean active) {
		super();
		this.lon = lon;
		this.lat = lat;
		this.cousine = cousine;
		this.petFriendly = false;
		this.occassion = occassion;
		this.prices = prices;
		this.goingByCar = false;
		this.date = date;
		this.accessForDisabled = false;
		this.servingAlcohol = false;
		this.liveMusic = false;
		this.musicGenre = musicGenre;
		this.hasBusinessHall = false;
		this.hasWifi = false;
		this.hasCarPark = false;
		this.hasSmokingPart = false;
		this.hasPlayground = false;
		this.hasGarden = false;
		this.active = active;
	}






	public boolean isActive() {
		return active;
	}






	public void setActive(boolean active) {
		this.active = active;
	}






	public RestaurantRequirements(double lon, double lat, Cousine cousine, boolean petFriendly, Occassion occassion,
			Prices prices, boolean goingByCar, Date date, boolean accessForDisabled, boolean servingAlcohol,
			boolean liveMusic, MusicGenre musicGenre, boolean hasBusinessHall, boolean hasWifi, boolean hasCarPark,
			boolean hasSmokingPart, boolean hasPlayground, boolean hasGarden) {
		super();
		this.lon = lon;
		this.lat = lat;
		this.cousine = cousine;
		this.petFriendly = petFriendly;
		this.occassion = occassion;
		this.prices = prices;
		this.goingByCar = goingByCar;
		this.date = date;
		this.accessForDisabled = accessForDisabled;
		this.servingAlcohol = servingAlcohol;
		this.liveMusic = liveMusic;
		this.musicGenre = musicGenre;
		this.hasBusinessHall = hasBusinessHall;
		this.hasWifi = hasWifi;
		this.hasCarPark = hasCarPark;
		this.hasSmokingPart = hasSmokingPart;
		this.hasPlayground = hasPlayground;
		this.hasGarden = hasGarden;
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


	public boolean isAccessForDisabled() {
		return accessForDisabled;
	}

	public void setAccessForDisabled(boolean accessForDisabled) {
		this.accessForDisabled = accessForDisabled;
	}

	public boolean isServingAlcohol() {
		return servingAlcohol;
	}

	public void setServingAlcohol(boolean servingAlcohol) {
		this.servingAlcohol = servingAlcohol;
	}

	public boolean isLiveMusic() {
		return liveMusic;
	}

	public void setLiveMusic(boolean liveMusic) {
		this.liveMusic = liveMusic;
	}

	public MusicGenre getMusicGenre() {
		return musicGenre;
	}

	public void setMusicGenre(MusicGenre musicGenre) {
		this.musicGenre = musicGenre;
	}



	public boolean isHasBusinessHall() {
		return hasBusinessHall;
	}



	public void setHasBusinessHall(boolean hasBusinessHall) {
		this.hasBusinessHall = hasBusinessHall;
	}



	public boolean isHasWifi() {
		return hasWifi;
	}



	public void setHasWifi(boolean hasWifi) {
		this.hasWifi = hasWifi;
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




	public boolean isHasPlayground() {
		return hasPlayground;
	}




	public void setHasPlayground(boolean hasPlayground) {
		this.hasPlayground = hasPlayground;
	}




	public boolean isHasGarden() {
		return hasGarden;
	}




	public void setHasGarden(boolean hasGarden) {
		this.hasGarden = hasGarden;
	}






	public boolean isGoingByCar() {
		return goingByCar;
	}






	public void setGoingByCar(boolean goingByCar) {
		this.goingByCar = goingByCar;
	}






	public Date getDate() {
		return date;
	}






	public void setDate(Date date) {
		this.date = date;
	}
    
    
    
    
}
