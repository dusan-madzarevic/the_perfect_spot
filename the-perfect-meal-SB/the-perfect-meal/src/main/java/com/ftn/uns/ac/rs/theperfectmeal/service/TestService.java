package com.ftn.uns.ac.rs.theperfectmeal.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.uns.ac.rs.theperfectmeal.model.User;
import com.ftn.uns.ac.rs.theperfectmeal.repository.TestRepository;

@Service
public class TestService {

	@Autowired
	private KieContainer kieContainer;
	
	@Autowired
	private TestRepository repository;
	
	public User test(User registeredUser) {
		KieSession kieSession = kieContainer.newKieSession();
		repository.save(registeredUser);
		kieSession.insert(registeredUser);
		kieSession.fireAllRules();
		kieSession.dispose();
		return registeredUser;
	}
	
}
