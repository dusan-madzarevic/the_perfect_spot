package com.ftn.uns.ac.rs.theperfectmeal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.uns.ac.rs.theperfectmeal.model.Alarm;
import com.ftn.uns.ac.rs.theperfectmeal.repository.AlarmRepository;

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
}
