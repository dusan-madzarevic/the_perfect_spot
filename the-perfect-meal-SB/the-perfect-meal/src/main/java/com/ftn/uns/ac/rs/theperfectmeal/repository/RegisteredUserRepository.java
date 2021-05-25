package com.ftn.uns.ac.rs.theperfectmeal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.uns.ac.rs.theperfectmeal.model.RegisteredUser;

public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, Long>{

	RegisteredUser findByEmail(String email);

}
