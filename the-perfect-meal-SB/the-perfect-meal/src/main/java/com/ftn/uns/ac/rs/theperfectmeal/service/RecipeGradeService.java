package com.ftn.uns.ac.rs.theperfectmeal.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.uns.ac.rs.theperfectmeal.dto.RecipeGradeDTO;
import com.ftn.uns.ac.rs.theperfectmeal.model.Recipe;
import com.ftn.uns.ac.rs.theperfectmeal.model.RecipeGrade;
import com.ftn.uns.ac.rs.theperfectmeal.model.RegisteredUser;
import com.ftn.uns.ac.rs.theperfectmeal.repository.RecipeGradeRepository;
import com.ftn.uns.ac.rs.theperfectmeal.repository.RecipeRepository;
import com.ftn.uns.ac.rs.theperfectmeal.repository.RegisteredUserRepository;

@Service
public class RecipeGradeService {

	@Autowired
	private RecipeGradeRepository gradeRepository;
	
	@Autowired
	private RegisteredUserRepository userRepository;
	
	@Autowired
	private RecipeRepository recipeRepository;
	
	public int getByRecipeAndUser(long recipeId, String email) {

		RegisteredUser loggedUser = this.userRepository.findByEmail(email);
		RecipeGrade grade = this.gradeRepository.getByRecipeAndUser(recipeId, loggedUser.getId());
		if (grade == null) {
			return 0;
		}
		return grade.getValue();
	}

	public boolean save(RecipeGradeDTO dto) {
		RegisteredUser loggedUser = this.userRepository.findByEmail(dto.getEmail());
		long userId = loggedUser.getId();
		//check if user has already rated restaurant
		RecipeGrade rg = this.gradeRepository.findByUserIdAndRecipeRecipeId(userId,dto.getRecipeId());
		if (rg != null) {
			//already graded, edit it
			rg.setDate(new Date());
			rg.setValue(dto.getGrade());
			this.gradeRepository.save(rg);
			return true;
		}
		
		//not graded yet, add grade to database
		RecipeGrade gr = new RecipeGrade();
		Recipe recipe = this.recipeRepository.findById(dto.getRecipeId()).orElse(null);
		List<RecipeGrade> grades = recipe.getRecipeGrades();
		gr.setDate(new Date());
		gr.setUser(loggedUser);
		gr.setId(44);
		gr.setRecipe(recipe);
		gr.setValue(dto.getGrade());
		grades.add(gr);
		//this.gradeRepository.save(gr);
		this.recipeRepository.save(recipe);
		return true;		
	}
	
}
