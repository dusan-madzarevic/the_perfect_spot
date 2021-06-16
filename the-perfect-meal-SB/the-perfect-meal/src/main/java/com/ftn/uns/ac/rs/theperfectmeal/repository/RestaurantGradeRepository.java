package com.ftn.uns.ac.rs.theperfectmeal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ftn.uns.ac.rs.theperfectmeal.model.RestaurantGrade;

public interface RestaurantGradeRepository extends JpaRepository<RestaurantGrade, Long>{

	@Query(value="select * from restaurant_grade where restaurant_restaurant_id = :restId and user_user_id = :userId",nativeQuery = true)
	public RestaurantGrade getByRestaurantAndUser(@Param("restId") double restId, @Param("userId") double userId);
	
	public RestaurantGrade findByUserIdAndRestaurantId(long id, long restId);

}
