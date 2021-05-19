package com.ftn.uns.ac.rs.theperfectmeal.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.uns.ac.rs.theperfectmeal.model.User;

@Service
public class TestService {

	@Autowired
	private KieContainer kieContainer;
	
	public User test(User registeredUser) {
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.insert(registeredUser);
		kieSession.fireAllRules();
		kieSession.dispose();
		return registeredUser;
	}
	
}
