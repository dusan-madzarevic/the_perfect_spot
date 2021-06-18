package com.ftn.uns.ac.rs.theperfectmeal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ftn.uns.ac.rs.theperfectmeal.dto.RecipeDTO;
import com.ftn.uns.ac.rs.theperfectmeal.model.Alarm;
import com.ftn.uns.ac.rs.theperfectmeal.service.AlarmService;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/alarm")
public class AlarmController {
	
	@Autowired
	private AlarmService alarmService;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value = "/by-page/{pageNum}")
	public ResponseEntity<Page<Alarm>> findAll(@PathVariable int pageNum){
		
		Pageable pageable = PageRequest.of(pageNum, 12, Sort.by("id").descending());
		
		Page<Alarm> page = this.alarmService.findAll(pageable);
		return new ResponseEntity<Page<Alarm>>(page, HttpStatus.OK);
	}

}
