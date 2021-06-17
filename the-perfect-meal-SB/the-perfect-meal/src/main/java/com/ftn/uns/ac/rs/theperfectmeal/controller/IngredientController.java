package com.ftn.uns.ac.rs.theperfectmeal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.uns.ac.rs.theperfectmeal.dto.IngredientDTO;
import com.ftn.uns.ac.rs.theperfectmeal.service.IngredientService;

@RestController
@RequestMapping(path = "/ingredients")
public class IngredientController {

	@Autowired
	private IngredientService ingredientService;
	
	@GetMapping()
	public ResponseEntity<List<IngredientDTO>> getAll(){
		
		return ResponseEntity.ok().body(ingredientService.getAll());
		
		
	}
	
}
