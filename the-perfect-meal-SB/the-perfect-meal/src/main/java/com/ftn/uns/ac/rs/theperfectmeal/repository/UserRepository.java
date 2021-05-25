package com.ftn.uns.ac.rs.theperfectmeal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.uns.ac.rs.theperfectmeal.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByEmail(String email);
}
