package com.ftn.uns.ac.rs.theperfectmeal.helper;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.uns.ac.rs.theperfectmeal.dto.UserDTO;
import com.ftn.uns.ac.rs.theperfectmeal.model.Authority;
import com.ftn.uns.ac.rs.theperfectmeal.model.RegisteredUser;
import com.ftn.uns.ac.rs.theperfectmeal.repository.AuthorityRepository;


@Component
public class RegisteredUserMapper implements MapperInterface<RegisteredUser, UserDTO> {

	@Autowired
	AuthorityRepository authRepository;
	
	@Override
	public RegisteredUser toEntity(UserDTO dto) {
		RegisteredUser r = new RegisteredUser();
		
		r.setEmail(dto.getEmail());
		r.setPassword(dto.getPassword());
		Authority auth = authRepository.findByName("ROLE_USER");
		ArrayList<Authority> aL = new ArrayList<>();
		aL.add(auth);
		r.setAuthorities(aL);
		r.setFirstName(dto.getFirstName());
		r.setLastName(dto.getLastName());
		
		return r;
	}

	@Override
	public UserDTO toDto(RegisteredUser entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
