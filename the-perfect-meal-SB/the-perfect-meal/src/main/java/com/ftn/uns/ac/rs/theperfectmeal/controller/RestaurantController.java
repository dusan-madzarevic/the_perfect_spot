package com.ftn.uns.ac.rs.theperfectmeal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.uns.ac.rs.theperfectmeal.dto.MessageResponse;
import com.ftn.uns.ac.rs.theperfectmeal.dto.RestaurantRequirements;
import com.ftn.uns.ac.rs.theperfectmeal.dto.RestaurantRequirementsDTO;
import com.ftn.uns.ac.rs.theperfectmeal.service.RestaurantService;

@RestController
@RequestMapping(path = "/restaurant")
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;
	
	@CrossOrigin
	@PostMapping("/process")
	public ResponseEntity<MessageResponse> processRequirements(@RequestBody RestaurantRequirements request){
		
		return ResponseEntity.ok().body(restaurantService.process(request));
		
	}
}
