package com.ftn.uns.ac.rs.theperfectmeal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ftn.uns.ac.rs.theperfectmeal.model.RecipeGrade;

public interface RecipeGradeRepository extends JpaRepository<RecipeGrade, Long> {

	@Query(value = "select * from recipe_grade where recipe_recipe_id = ?1 and user_user_id = ?2", nativeQuery = true)
	public RecipeGrade getByRecipeAndUser(double recipeId, double userId);

	public RecipeGrade findByUserIdAndRecipeRecipeId(long id, long recipeId);

}
