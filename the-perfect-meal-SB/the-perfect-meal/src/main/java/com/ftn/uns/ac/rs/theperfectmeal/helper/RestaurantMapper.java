package com.ftn.uns.ac.rs.theperfectmeal.helper;

import org.springframework.stereotype.Component;

import com.ftn.uns.ac.rs.theperfectmeal.dto.RestaurantDTO;
import com.ftn.uns.ac.rs.theperfectmeal.model.Restaurant;

@Component
public class RestaurantMapper implements MapperInterface<Restaurant, RestaurantDTO> {

	@Override
	public Restaurant toEntity(RestaurantDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RestaurantDTO toDto(Restaurant entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
