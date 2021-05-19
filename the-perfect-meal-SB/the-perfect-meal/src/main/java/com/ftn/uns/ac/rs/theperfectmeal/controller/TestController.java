package com.ftn.uns.ac.rs.theperfectmeal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.uns.ac.rs.theperfectmeal.dto.UserDTO;
import com.ftn.uns.ac.rs.theperfectmeal.model.User;
import com.ftn.uns.ac.rs.theperfectmeal.service.TestService;

@RestController
@RequestMapping(path = "/test")
public class TestController {

	@Autowired
	private TestService testService;

	@PostMapping
	public ResponseEntity<User> register(@RequestBody UserDTO userDto) {

		User freshUser = new User(userDto.getFirstName(),userDto.getLastName(),userDto.getEmail(),userDto.getPassword());
		
		User newUser = this.testService.test(freshUser);
		
		return new ResponseEntity<>(newUser, HttpStatus.OK);
	}
	
	
}
