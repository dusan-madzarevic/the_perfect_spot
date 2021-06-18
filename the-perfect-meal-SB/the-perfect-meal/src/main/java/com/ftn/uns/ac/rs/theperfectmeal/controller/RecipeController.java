package com.ftn.uns.ac.rs.theperfectmeal.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.maven.shared.invoker.MavenInvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.uns.ac.rs.theperfectmeal.dto.MessageResponse;
import com.ftn.uns.ac.rs.theperfectmeal.dto.RecipeDTO;
import com.ftn.uns.ac.rs.theperfectmeal.dto.RecipeRequirements;
import com.ftn.uns.ac.rs.theperfectmeal.dto.RestaurantDTO;
import com.ftn.uns.ac.rs.theperfectmeal.model.Cuisine;
import com.ftn.uns.ac.rs.theperfectmeal.model.Prices;
import com.ftn.uns.ac.rs.theperfectmeal.service.RecipeService;
import com.ftn.uns.ac.rs.theperfectmeal.util.PageImplementation;
import com.ftn.uns.ac.rs.theperfectmeal.util.RecipeType;

@RestController
@RequestMapping(path = "/recipe")
public class RecipeController {

	@Autowired
	private RecipeService recipeService;
	
	@CrossOrigin
	@PostMapping("/process")
	public ResponseEntity<MessageResponse> processRequirements(@RequestBody RecipeRequirements request) throws IOException, MavenInvocationException{
		
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
	
	@GetMapping(value = "/by-page/{pageNum}")
	public ResponseEntity<PageImplementation<RecipeDTO>> getAll(@PathVariable int pageNum){
		Pageable pageable = PageRequest.of(pageNum, 6);
		
		PageImplementation<RecipeDTO> pageImpl = this.recipeService.findAll(pageable);
		return new ResponseEntity<PageImplementation<RecipeDTO>>(pageImpl, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<RecipeDTO> getOne(@PathVariable long id){
		RecipeDTO dto = this.recipeService.getOne(id);
		return new ResponseEntity<RecipeDTO>(dto, HttpStatus.OK);
	}
	
	@GetMapping(value = "/filter/by-page/{pageNum}")
	public ResponseEntity<PageImplementation<RecipeDTO>> filter(@PathVariable int pageNum, @RequestParam("dishTypes") ArrayList<RecipeType> dishTypes) throws FileNotFoundException{
		Pageable pageable = PageRequest.of(pageNum, 6);
		PageImplementation<RecipeDTO> pageImpl = this.recipeService.filter(pageable, dishTypes);
		return new ResponseEntity<PageImplementation<RecipeDTO>>(pageImpl, HttpStatus.OK);
	}
	
	@GetMapping(value = "/search/by-page/{pageNum}")
	public ResponseEntity<PageImplementation<RecipeDTO>> search(@PathVariable int pageNum, @RequestParam("restName") String restName ) throws FileNotFoundException{
		Pageable pageable = PageRequest.of(pageNum, 6);
		PageImplementation<RecipeDTO> pageImpl = this.recipeService.search(pageable,restName);
		return new ResponseEntity<PageImplementation<RecipeDTO>>(pageImpl, HttpStatus.OK);
	}
	
}
