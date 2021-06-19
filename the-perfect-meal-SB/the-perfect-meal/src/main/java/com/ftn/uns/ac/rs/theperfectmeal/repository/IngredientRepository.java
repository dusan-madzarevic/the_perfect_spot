package com.ftn.uns.ac.rs.theperfectmeal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ftn.uns.ac.rs.theperfectmeal.model.Ingredient;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

	@Query(value = "DELETE FROM recipe_ingredient WHERE recipe_recipe_id = ?1", nativeQuery = true)
	@Modifying
	public void deleteIngredients(long recipeId);
	
}
