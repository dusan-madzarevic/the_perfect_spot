package com.ftn.uns.ac.rs.theperfectmeal.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ftn.uns.ac.rs.theperfectmeal.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

	@Query(value = "select * from the_perfect_meal.restaurant re where lower(re.name) like :restName%", nativeQuery = true)
	public Page<Restaurant> searchByName(Pageable pageRequest, @Param("restName") String restName);



}
