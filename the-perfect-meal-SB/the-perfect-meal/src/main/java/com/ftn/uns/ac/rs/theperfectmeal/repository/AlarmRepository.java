package com.ftn.uns.ac.rs.theperfectmeal.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.uns.ac.rs.theperfectmeal.model.Alarm;

@Repository
public interface AlarmRepository extends JpaRepository<Alarm, Long> {


	

	
}
