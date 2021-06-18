package com.ftn.uns.ac.rs.theperfectmeal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.uns.ac.rs.theperfectmeal.dto.IngredientDTO;
import com.ftn.uns.ac.rs.theperfectmeal.helper.IngredientMapper;
import com.ftn.uns.ac.rs.theperfectmeal.repository.IngredientRepository;

@Service
public class IngredientService {

	@Autowired
	private IngredientRepository ingredientRepository;
	
	@Autowired
	private IngredientMapper ingredientMapper;
	
	public List<IngredientDTO> getAll(){
		
		return ingredientMapper.toDtoList(ingredientRepository.findAll());
		
	}
	
}
