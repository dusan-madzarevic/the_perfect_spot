package com.ftn.uns.ac.rs.theperfectmeal.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.maven.shared.invoker.MavenInvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.uns.ac.rs.theperfectmeal.dto.RecipeAvgGradeDTO;
import com.ftn.uns.ac.rs.theperfectmeal.dto.RecipeDTO;
import com.ftn.uns.ac.rs.theperfectmeal.dto.RecipeEditDTO;
import com.ftn.uns.ac.rs.theperfectmeal.dto.RecipeRequirements;
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
	public ResponseEntity<RecipeDTO> processRequirements(@RequestBody RecipeRequirements request) throws IOException, MavenInvocationException{
		
		return ResponseEntity.ok().body(recipeService.process(request));
		
	}
	
	@CrossOrigin
	@GetMapping("/report/best-graded")
	public ResponseEntity<ArrayList<RecipeDTO>> bestGraded(){
		
		return ResponseEntity.ok().body(recipeService.bestGradedRecipe());
		
	}
	
	@CrossOrigin
	@GetMapping("/report/best-graded-last-month")
	public ResponseEntity<ArrayList<RecipeDTO>> bestGradedLastMonth(){
		
		return ResponseEntity.ok().body(recipeService.bestGradedRecipeLastMonth());
		
	}
	
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/report/most-recommended")
	public ResponseEntity<List<RecipeDTO>> mostRecommended() {
		List<RecipeDTO> dtos = new ArrayList<RecipeDTO>();
		dtos = this.recipeService.mostRecommended();
		return new ResponseEntity<List<RecipeDTO>>(dtos, HttpStatus.OK);
	}
	
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/report/in-grade-range")
	public ResponseEntity<List<RecipeDTO>> inGradeRange(@RequestParam("minGrade") double minGrade, @RequestParam("maxGrade") double maxGrade) throws FileNotFoundException {
		List<RecipeDTO> dtos = new ArrayList<RecipeDTO>();
		dtos = this.recipeService.getRecipesInGradeRange(minGrade, maxGrade);
		return new ResponseEntity<List<RecipeDTO>>(dtos, HttpStatus.OK);
	}
	
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/report/average-grade-in-data-range")
	public ResponseEntity<List<RecipeAvgGradeDTO>> avgGradeInDataRange(@RequestParam("dateFrom") String dateFrom, @RequestParam("dateTo") String dateTo) throws FileNotFoundException, ParseException {
		List<RecipeAvgGradeDTO> dtos = new ArrayList<RecipeAvgGradeDTO>();
		
		dtos = this.recipeService.getAvgGradeInDateRange(java.time.LocalDate.parse(dateFrom), java.time.LocalDate.parse(dateTo));
		return new ResponseEntity<List<RecipeAvgGradeDTO>>(dtos, HttpStatus.OK);
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
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping
	public ResponseEntity<HttpStatus> create(@RequestBody RecipeDTO restaurant) throws FileNotFoundException {
		boolean ok = this.recipeService.create(restaurant);
		if(ok)
			return new ResponseEntity<>(HttpStatus.OK);
		return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(value = "/{id}")
	public ResponseEntity<HttpStatus> update(@PathVariable long id, @RequestBody RecipeEditDTO restaurant) throws FileNotFoundException {
		boolean ok = this.recipeService.update(restaurant);
		if(ok)
			return new ResponseEntity<>(HttpStatus.OK);
		return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable long id) throws FileNotFoundException {
		boolean ok = this.recipeService.delete(id);
		if(ok)
			return new ResponseEntity<>(HttpStatus.OK);
		return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		
	}
	
}
