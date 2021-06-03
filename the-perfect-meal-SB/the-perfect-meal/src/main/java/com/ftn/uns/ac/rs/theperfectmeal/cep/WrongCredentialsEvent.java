package com.ftn.uns.ac.rs.theperfectmeal.cep;

import java.time.LocalDate;
import java.util.Date;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import com.ftn.uns.ac.rs.theperfectmeal.model.User;

@Role(Role.Type.EVENT)
@Timestamp("timestamp")
@Expires("1m")
public class WrongCredentialsEvent {

	private Date timestamp;
	private User user;

	public WrongCredentialsEvent() {
		super();
	}

	public WrongCredentialsEvent(Date timestamp, User user) {
		super();
		this.timestamp = timestamp;
		this.user = user;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
