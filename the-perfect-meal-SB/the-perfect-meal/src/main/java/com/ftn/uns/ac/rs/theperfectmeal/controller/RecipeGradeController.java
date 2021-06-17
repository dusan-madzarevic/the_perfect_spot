package com.ftn.uns.ac.rs.theperfectmeal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.uns.ac.rs.theperfectmeal.dto.RecipeGradeDTO;
import com.ftn.uns.ac.rs.theperfectmeal.service.RecipeGradeService;

@RestController
@RequestMapping(path = "/recipe-grade")
public class RecipeGradeController {

	@Autowired
	private RecipeGradeService gradeService;
	
   // @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(value = "/by-recipe")
	public ResponseEntity<Integer> getByRecipeAndUser(@RequestParam("recipeId") int recipeId, @RequestParam("email") String email){

		
		Integer grade = this.gradeService.getByRecipeAndUser(recipeId,email);
		return new ResponseEntity<>(grade, HttpStatus.OK);
	}

   // @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping(value = "/grade")
    public ResponseEntity<Boolean> save(@RequestBody RecipeGradeDTO dto){
    	boolean ok = this.gradeService.save(dto);
    	if(ok)
    		return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		return new ResponseEntity<Boolean>(true,HttpStatus.INTERNAL_SERVER_ERROR);

    }
	
}
