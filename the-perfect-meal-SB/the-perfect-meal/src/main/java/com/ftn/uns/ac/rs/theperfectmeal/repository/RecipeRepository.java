package com.ftn.uns.ac.rs.theperfectmeal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.uns.ac.rs.theperfectmeal.model.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

}
