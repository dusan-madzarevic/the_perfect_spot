package com.ftn.uns.ac.rs.theperfectmeal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.uns.ac.rs.theperfectmeal.dto.MessageResponse;
import com.ftn.uns.ac.rs.theperfectmeal.dto.RecipeRequirements;
import com.ftn.uns.ac.rs.theperfectmeal.service.RecipeService;

@RestController
@RequestMapping(path = "/recipe")
public class RecipeController {

	@Autowired
	private RecipeService recipeService;
	
	@CrossOrigin
	@PostMapping("/process")
	public ResponseEntity<MessageResponse> processRequirements(@RequestBody RecipeRequirements request){
		
		return ResponseEntity.ok().body(recipeService.process(request));
		
	}
	
}
