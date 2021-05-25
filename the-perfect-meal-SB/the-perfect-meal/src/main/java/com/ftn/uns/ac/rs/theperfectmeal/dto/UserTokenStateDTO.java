package com.ftn.uns.ac.rs.theperfectmeal.dto;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import com.ftn.uns.ac.rs.theperfectmeal.model.Authority;

// DTO koji enkapsulira generisani JWT i njegovo trajanje koji se vracaju klijentu
public class UserTokenStateDTO {

	private String authenticationToken;
	private int expiresAt;
	private String email;

	

    public UserTokenStateDTO() {
    }

    public UserTokenStateDTO(String authenticationToken, int expiresAt) {
		super();
		this.authenticationToken = authenticationToken;
		this.expiresAt = expiresAt;
	}
    
  
    
    public UserTokenStateDTO(String authenticationToken, int expiresAt, String email) {
		super();
		this.authenticationToken = authenticationToken;
		this.expiresAt = expiresAt;
		this.email = email;

	}


    
	


	public String getAuthenticationToken() {
		return authenticationToken;
	}

	public void setAuthenticationToken(String authenticationToken) {
		this.authenticationToken = authenticationToken;
	}

	public int getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(int expiresAt) {
		this.expiresAt = expiresAt;
	}

	public String getEmail() { 
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

    
}
