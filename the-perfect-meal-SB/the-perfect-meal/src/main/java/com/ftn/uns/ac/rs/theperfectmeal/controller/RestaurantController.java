package com.ftn.uns.ac.rs.theperfectmeal.controller;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.ftn.uns.ac.rs.theperfectmeal.dto.RestaurantAvgGradeDto;
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

	@PreAuthorize("hasRole('ROLE_USER')")
	@PostMapping("/process")
	public ResponseEntity<RestaurantDTO> processRequirements(@RequestBody RestaurantRequirementsDTO request) {

		RestaurantDTO dto = this.restaurantService.process(request);

		return ResponseEntity.ok().body(dto);

	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/report/best-graded-last-month")
	public ResponseEntity<List<RestaurantAvgGradeDto>> bestGradedLastMonth() {
		List<RestaurantAvgGradeDto> dtos = new ArrayList<RestaurantAvgGradeDto>();
		dtos = this.restaurantService.bestGradedLastMonth();
		return new ResponseEntity<List<RestaurantAvgGradeDto>>(dtos, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/report/best-graded")
	public ResponseEntity<List<RestaurantDTO>> bestGraded() {
		List<RestaurantDTO> dtos = new ArrayList<RestaurantDTO>();
		dtos = this.restaurantService.bestGraded();
		return new ResponseEntity<List<RestaurantDTO>>(dtos, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/report/most-recommended")
	public ResponseEntity<List<RestaurantDTO>> mostRecommended() {
		List<RestaurantDTO> dtos = new ArrayList<RestaurantDTO>();
		dtos = this.restaurantService.mostRecommended();
		return new ResponseEntity<List<RestaurantDTO>>(dtos, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/report/in-grade-range")
	public ResponseEntity<List<RestaurantDTO>> inGradeRange(@RequestParam("minGrade") double minGrade, @RequestParam("maxGrade") double maxGrade) throws FileNotFoundException {
		List<RestaurantDTO> dtos = new ArrayList<RestaurantDTO>();
		dtos = this.restaurantService.getRestaurantsInGradeRange(minGrade, maxGrade);
		return new ResponseEntity<List<RestaurantDTO>>(dtos, HttpStatus.OK);
	}
	
    @PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/report/average-grade-in-data-range")
	public ResponseEntity<List<RestaurantAvgGradeDto>> avgGradeInDataRange(@RequestParam("dateFrom") String dateFrom, @RequestParam("dateTo") String dateTo) throws FileNotFoundException, ParseException {
		List<RestaurantAvgGradeDto> dtos = new ArrayList<RestaurantAvgGradeDto>();
		
		dtos = this.restaurantService.getAvgGradeInDateRange(java.time.LocalDate.parse(dateFrom), java.time.LocalDate.parse(dateTo));
		return new ResponseEntity<List<RestaurantAvgGradeDto>>(dtos, HttpStatus.OK);
	}

	@GetMapping(value = "/by-page/{pageNum}")
	public ResponseEntity<PageImplementation<RestaurantDTO>> getAll(@PathVariable int pageNum) {
		Pageable pageable = PageRequest.of(pageNum, 6);

		PageImplementation<RestaurantDTO> pageImpl = this.restaurantService.findAll(pageable);
		return new ResponseEntity<PageImplementation<RestaurantDTO>>(pageImpl, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<RestaurantDTO> getOne(@PathVariable long id) {
		RestaurantDTO dto = this.restaurantService.getOne(id);
		return new ResponseEntity<RestaurantDTO>(dto, HttpStatus.OK);
	}

	@GetMapping(value = "/filter/by-page/{pageNum}")
	public ResponseEntity<PageImplementation<RestaurantDTO>> filter(@PathVariable int pageNum,
			@RequestParam("prices") ArrayList<Prices> prices, @RequestParam("cuisines") ArrayList<Cuisine> cuisines)
			throws FileNotFoundException {
		Pageable pageable = PageRequest.of(pageNum, 6);
		PageImplementation<RestaurantDTO> pageImpl = this.restaurantService.filter(pageable, cuisines, prices);
		return new ResponseEntity<PageImplementation<RestaurantDTO>>(pageImpl, HttpStatus.OK);
	}

	@GetMapping(value = "/search/by-page/{pageNum}")
	public ResponseEntity<PageImplementation<RestaurantDTO>> search(@PathVariable int pageNum,
			@RequestParam("restName") String restName) throws FileNotFoundException {
		Pageable pageable = PageRequest.of(pageNum, 6);
		PageImplementation<RestaurantDTO> pageImpl = this.restaurantService.search(pageable, restName);
		return new ResponseEntity<PageImplementation<RestaurantDTO>>(pageImpl, HttpStatus.OK);
	}
}
