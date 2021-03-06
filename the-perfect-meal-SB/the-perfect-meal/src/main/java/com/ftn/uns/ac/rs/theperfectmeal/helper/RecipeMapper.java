package com.ftn.uns.ac.rs.theperfectmeal.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.uns.ac.rs.theperfectmeal.dto.IngredientAmount;
import com.ftn.uns.ac.rs.theperfectmeal.dto.RecipeDTO;
import com.ftn.uns.ac.rs.theperfectmeal.dto.RecipeIngredientDTO;
import com.ftn.uns.ac.rs.theperfectmeal.model.Ingredient;
import com.ftn.uns.ac.rs.theperfectmeal.model.Recipe;
import com.ftn.uns.ac.rs.theperfectmeal.model.RecipeIngredient;
import com.ftn.uns.ac.rs.theperfectmeal.model.RecipeIngredientKey;
import com.ftn.uns.ac.rs.theperfectmeal.repository.IngredientRepository;
import com.ftn.uns.ac.rs.theperfectmeal.util.RecipeType;

@Component
public class RecipeMapper implements MapperInterface<Recipe, RecipeDTO> {

	@Autowired
	private IngredientMapper ingredientMapper;
	
	@Autowired
	private IngredientRepository ingredientRepository;
	
	@Override
	public Recipe toEntity(RecipeDTO dto) {
		
		Recipe recipe = new Recipe();
		recipe.setRecipeName(dto.getName());
		recipe.setStepsText(dto.getStepsText());
		recipe.setStepsNumber(dto.getStepsNumber());
		recipe.setServings(dto.getServings());
		recipe.setPrepDuration(dto.getPrepDuration());
		recipe.setType(RecipeType.values()[Integer.parseInt(dto.getType())]);

		return recipe;
	}

	@Override
	public RecipeDTO toDto(Recipe entity) {
		RecipeDTO dto = new RecipeDTO();
		if(entity.getImage() == null)
			dto.setImage(null);
		else
			dto.setImage(entity.getImage());
		dto.setPrepDuration(entity.getPrepDuration());
		dto.setId(entity.getRecipeId());
		dto.setName(entity.getRecipeName());
		dto.setServings(entity.getServings());
		dto.setStepsNumber(entity.getStepsNumber());
		dto.setStepsText(entity.getStepsText());
		dto.setType(entity.getType().toString());
		dto.setIngredients(ingredientMapper.toDtoListFromRecipe(entity.getIngredients()));
		dto.setGrade(entity.getRecipeGrades().size() > 0 ? entity.getRecipeGrades().stream().mapToDouble(g -> g.getValue()).average().orElse(0) : 0);
		
		return dto;
	}


	@Override
	public List<Recipe> toEntityList(List<RecipeDTO> dtoList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecipeDTO> toDtoList(List<Recipe> entityList) {
		
		List<RecipeDTO> dtos = new ArrayList<RecipeDTO>();
		
		for (Recipe entity : entityList) {
			
			RecipeDTO dto = new RecipeDTO();
			dto.setImage(entity.getImage());
			dto.setPrepDuration(entity.getPrepDuration());
			dto.setId(entity.getRecipeId());
			dto.setName(entity.getRecipeName());
			dto.setServings(entity.getServings());
			dto.setStepsNumber(entity.getStepsNumber());
			dto.setStepsText(entity.getStepsText());
			dto.setType(entity.getType().toString());
			dto.setIngredients(ingredientMapper.toDtoListFromRecipe(entity.getIngredients()));
			dto.setGrade(entity.getRecipeGrades().size() > 0 ? entity.getRecipeGrades().stream().mapToDouble(g -> g.getValue()).average().orElse(0) : 0);

			
			dtos.add(dto);
		}
		
		return dtos;
		
	}

}
