package com.ftn.uns.ac.rs.theperfectmeal.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ftn.uns.ac.rs.theperfectmeal.dto.IngredientDTO;
import com.ftn.uns.ac.rs.theperfectmeal.dto.RecipeIngredientDTO;
import com.ftn.uns.ac.rs.theperfectmeal.model.Ingredient;
import com.ftn.uns.ac.rs.theperfectmeal.model.RecipeIngredient;

@Component
public class IngredientMapper implements MapperInterface<Ingredient, IngredientDTO> {

	@Override
	public Ingredient toEntity(IngredientDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IngredientDTO toDto(Ingredient entity) {
		
		IngredientDTO dto = new IngredientDTO(entity.getIngredientId(), entity.getName());
		return dto;
		
	}

	@Override
	public List<Ingredient> toEntityList(List<IngredientDTO> dtoList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IngredientDTO> toDtoList(List<Ingredient> entityList) {
		
		List<IngredientDTO> dtos = new ArrayList<IngredientDTO>();
		
		for (Ingredient ingredient : entityList) {
			
			IngredientDTO dto = new IngredientDTO(ingredient.getIngredientId(), ingredient.getName());
			dtos.add(dto);
		}
		return dtos;
		
	}
	
	public List<RecipeIngredientDTO> toDtoListFromRecipe(List<RecipeIngredient> entityList) {
		
		List<RecipeIngredientDTO> dtos = new ArrayList<RecipeIngredientDTO>();
		
		for (RecipeIngredient ingredient : entityList) {
			
			RecipeIngredientDTO dto = new RecipeIngredientDTO(ingredient.getIngredient().getIngredientId(), ingredient.getIngredient().getName(), ingredient.getAmount(), ingredient.getIngredient().getCaloricValue());
			dtos.add(dto);
		}
		return dtos;
		
	}

}
