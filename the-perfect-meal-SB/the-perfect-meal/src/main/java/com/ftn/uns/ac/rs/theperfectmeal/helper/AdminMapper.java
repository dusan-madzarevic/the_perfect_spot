package com.ftn.uns.ac.rs.theperfectmeal.helper;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.uns.ac.rs.theperfectmeal.dto.UserDTO;
import com.ftn.uns.ac.rs.theperfectmeal.model.Admin;
import com.ftn.uns.ac.rs.theperfectmeal.model.Authority;
import com.ftn.uns.ac.rs.theperfectmeal.repository.AuthorityRepository;


@Component
public class AdminMapper implements MapperInterface<Admin, UserDTO>{

	@Autowired
	AuthorityRepository authRepository;
	
	@Override
	public Admin toEntity(UserDTO dto) {
		Admin a = new Admin();
		
		a.setEmail(dto.getEmail());
		a.setPassword(dto.getPassword());
		Authority auth = authRepository.findByName("ROLE_ADMIN");
		ArrayList<Authority> aL = new ArrayList<>();
		aL.add(auth);
		a.setAuthorities(aL);
		a.setFirstName(dto.getFirstName());
		a.setLastName(dto.getLastName());
		
		return a;
	}

	@Override
	public UserDTO toDto(Admin entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
