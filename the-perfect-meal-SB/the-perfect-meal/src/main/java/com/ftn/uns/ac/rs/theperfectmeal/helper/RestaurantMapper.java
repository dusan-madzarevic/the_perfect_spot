package com.ftn.uns.ac.rs.theperfectmeal.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ftn.uns.ac.rs.theperfectmeal.dto.RestaurantDTO;
import com.ftn.uns.ac.rs.theperfectmeal.model.Restaurant;
import com.ftn.uns.ac.rs.theperfectmeal.model.RestaurantGrade;

@Component
public class RestaurantMapper implements MapperInterface<Restaurant, RestaurantDTO> {

	@Override
	public Restaurant toEntity(RestaurantDTO dto) {
		
		return null;
	}

	@Override
	public RestaurantDTO toDto(Restaurant entity) {
		RestaurantDTO dto = new RestaurantDTO();
		dto.setRecommendationCount(entity.getRecommendationCount());
		dto.setAddress(entity.getAddress());
		dto.setCuisine(entity.getCuisine());
		dto.setDescription(entity.getDescription());
		double sumOfGrades = 0;
		for(RestaurantGrade g: entity.getGrades()) {
			sumOfGrades += g.getValue();
		}
		if (entity.getGrades().size() > 0)
			dto.setGrade(sumOfGrades/entity.getGrades().size());
		else
			dto.setGrade(0);
		dto.setHasAccessForDisabled(entity.isAccessForDisabled());
		dto.setHasBusinessHall(entity.isHasBusinessHall());
		dto.setHasCarPark(entity.isHasCarPark());
		dto.setHasGarden(entity.isHasGarden());
		dto.setHasLiveMusic(entity.isLiveMusic());
		dto.setHasPlayground(entity.isHasPlayground());
		dto.setHasSmokingPart(entity.isHasSmokingPart());
		dto.setHasWifi(entity.isHasWifi());
		dto.setId(entity.getId());
		dto.setImage(entity.getImage());
		dto.setLat(entity.getLat());
		dto.setLon(entity.getLon());
		dto.setMusicGenre(entity.getMusicGenre());
		dto.setName(entity.getName());
		dto.setPetFriendly(entity.isPetFriendly());
		dto.setPhone(entity.getPhoneNumber());
		dto.setPrices(entity.getPrices());
		dto.setServingAlcohol(entity.isServingAlcohol());
		dto.setWebSite(entity.getWebSite());
		dto.setWorkingHours(entity.getWorkingHoursStart()+ "-" +entity.getWorkingHoursEnd());
		
		return dto;
	}

	@Override
	public List<Restaurant> toEntityList(List<RestaurantDTO> dtoList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RestaurantDTO> toDtoList(List<Restaurant> list) {
		List<RestaurantDTO> dtoList = new ArrayList<RestaurantDTO>();
		for(Restaurant r: list) {
			dtoList.add(this.toDto(r));
		}
		return dtoList;
	}

}
