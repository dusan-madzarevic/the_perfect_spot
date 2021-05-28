package com.ftn.uns.ac.rs.theperfectmeal.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.uns.ac.rs.theperfectmeal.dto.MessageResponse;
import com.ftn.uns.ac.rs.theperfectmeal.dto.RecipeDTO;
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
	
	@CrossOrigin
	@GetMapping("/bestGraded")
	public ResponseEntity<ArrayList<RecipeDTO>> bestGraded(){
		
		return ResponseEntity.ok().body(recipeService.bestGradedRecipe());
		
	}
	
	@CrossOrigin
	@GetMapping("/bestGradedLastMonth")
	public ResponseEntity<ArrayList<RecipeDTO>> bestGradedLastMonth(){
		
		return ResponseEntity.ok().body(recipeService.bestGradedRecipeLastMonth());
		
	}
}
