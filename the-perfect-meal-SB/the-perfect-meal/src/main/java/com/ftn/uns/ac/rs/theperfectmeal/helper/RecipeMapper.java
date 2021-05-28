package com.ftn.uns.ac.rs.theperfectmeal.helper;

import org.springframework.stereotype.Component;

import com.ftn.uns.ac.rs.theperfectmeal.dto.RecipeDTO;
import com.ftn.uns.ac.rs.theperfectmeal.model.Recipe;

@Component
public class RecipeMapper implements MapperInterface<Recipe, RecipeDTO> {

	@Override
	public Recipe toEntity(RecipeDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecipeDTO toDto(Recipe entity) {
		RecipeDTO dto = new RecipeDTO();
		dto.setImage(entity.getImage());
		dto.setPrepDuration(entity.getPrepDuration());
		dto.setRecipeId(entity.getRecipeId());
		dto.setRecipeName(entity.getRecipeName());
		dto.setServings(entity.getServings());
		dto.setStepsNumber(entity.getStepsNumber());
		dto.setStepsText(entity.getStepsText());
		dto.setType(entity.getType());
		
		return dto;
	}

}