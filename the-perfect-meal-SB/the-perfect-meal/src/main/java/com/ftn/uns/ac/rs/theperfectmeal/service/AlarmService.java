package com.ftn.uns.ac.rs.theperfectmeal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ftn.uns.ac.rs.theperfectmeal.dto.RestaurantDTO;
import com.ftn.uns.ac.rs.theperfectmeal.model.Alarm;
import com.ftn.uns.ac.rs.theperfectmeal.model.Restaurant;
import com.ftn.uns.ac.rs.theperfectmeal.repository.AlarmRepository;
import com.ftn.uns.ac.rs.theperfectmeal.util.PageImplMapper;
import com.ftn.uns.ac.rs.theperfectmeal.util.PageImplementation;


@Service
public class AlarmService {

	@Autowired
	private AlarmRepository alarmRepository;
	
	public Alarm save(Alarm a) {
		
		return alarmRepository.save(a);
		
	}

	public List<Alarm> getAll(){
		
		return alarmRepository.findAll();
		
	}

	public Page<Alarm> findAll(Pageable pageable) {
		Page<Alarm> page = this.alarmRepository.findAll(pageable);
		
		
		return page;
	}
}
