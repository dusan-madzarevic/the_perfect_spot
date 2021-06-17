package com.ftn.uns.ac.rs.theperfectmeal.controller;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.uns.ac.rs.theperfectmeal.dto.MessageResponse;
import com.ftn.uns.ac.rs.theperfectmeal.dto.RestaurantDTO;
import com.ftn.uns.ac.rs.theperfectmeal.dto.RestaurantRequirements;
import com.ftn.uns.ac.rs.theperfectmeal.dto.RestaurantRequirementsDTO;
import com.ftn.uns.ac.rs.theperfectmeal.model.Cuisine;
import com.ftn.uns.ac.rs.theperfectmeal.model.Prices;
import com.ftn.uns.ac.rs.theperfectmeal.service.RestaurantService;
import com.ftn.uns.ac.rs.theperfectmeal.util.PageImplementation;

@RestController
@RequestMapping(path = "/restaurant")
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;
	
    //@PreAuthorize("hasRole('ROLE_USER')")
	@PostMapping("/process")
	public ResponseEntity<RestaurantDTO> processRequirements(@RequestBody RestaurantRequirementsDTO request){
		System.out.println(request.getLat());
		System.out.println(request.getLon());
		System.out.println(request.getCuisine());
		System.out.println(request.getOccassion());
		System.out.println(request.getPrices());
		System.out.println(request.isAccessForDisabled());
		System.out.println(request.isGoingByCar());
		System.out.println(request.isPetFriendly());
		RestaurantDTO dto = this.restaurantService.process(request);

		return ResponseEntity.ok().body(dto);
		
	}
	
	@GetMapping(value = "/by-page/{pageNum}")
	public ResponseEntity<PageImplementation<RestaurantDTO>> getAll(@PathVariable int pageNum){
		Pageable pageable = PageRequest.of(pageNum, 6);
		
		PageImplementation<RestaurantDTO> pageImpl = this.restaurantService.findAll(pageable);
		return new ResponseEntity<PageImplementation<RestaurantDTO>>(pageImpl, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<RestaurantDTO> getOne(@PathVariable long id){
		RestaurantDTO dto = this.restaurantService.getOne(id);
		return new ResponseEntity<RestaurantDTO>(dto, HttpStatus.OK);
	}
	
	@GetMapping(value = "/filter/by-page/{pageNum}")
	public ResponseEntity<PageImplementation<RestaurantDTO>> filter(@PathVariable int pageNum, @RequestParam("prices") ArrayList<Prices> prices,@RequestParam("cuisines") ArrayList<Cuisine> cuisines) throws FileNotFoundException{
		Pageable pageable = PageRequest.of(pageNum, 6);
		PageImplementation<RestaurantDTO> pageImpl = this.restaurantService.filter(pageable,cuisines,prices);
		return new ResponseEntity<PageImplementation<RestaurantDTO>>(pageImpl, HttpStatus.OK);
	}
	
	@GetMapping(value = "/search/by-page/{pageNum}")
	public ResponseEntity<PageImplementation<RestaurantDTO>> search(@PathVariable int pageNum, @RequestParam("restName") String restName ) throws FileNotFoundException{
		Pageable pageable = PageRequest.of(pageNum, 6);
		PageImplementation<RestaurantDTO> pageImpl = this.restaurantService.search(pageable,restName);
		return new ResponseEntity<PageImplementation<RestaurantDTO>>(pageImpl, HttpStatus.OK);
	}
}
