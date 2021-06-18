package com.ftn.uns.ac.rs.theperfectmeal.controller;

import java.time.Instant;
import java.util.Date;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.uns.ac.rs.theperfectmeal.cep.BadRestaurantRatingEvent;
import com.ftn.uns.ac.rs.theperfectmeal.cep.WrongCredentialsEvent;
import com.ftn.uns.ac.rs.theperfectmeal.dto.RestaurantGradeDto;
import com.ftn.uns.ac.rs.theperfectmeal.service.KieStatefulSessionService;
import com.ftn.uns.ac.rs.theperfectmeal.service.RestaurantGradeService;
import com.ftn.uns.ac.rs.theperfectmeal.util.LoginAlarm;

@RestController
@RequestMapping(path = "/restaurant-grade")
public class RestaurantGradeController {
	
	@Autowired
	private RestaurantGradeService gradeService;
	
	
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(value = "/by-restaurant")
	public ResponseEntity<Integer> getByRestaurantAndUser(@RequestParam("restId") int restId, @RequestParam("email") String email){

		
		Integer grade = this.gradeService.getByRestaurantAndUser(restId,email);
		return new ResponseEntity<>(grade, HttpStatus.OK);
	}

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping(value = "/grade")
    public ResponseEntity<Boolean> save(@RequestBody RestaurantGradeDto dto){
    	boolean ok = this.gradeService.save(dto);
    	if(ok)
    		return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		return new ResponseEntity<Boolean>(true,HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
