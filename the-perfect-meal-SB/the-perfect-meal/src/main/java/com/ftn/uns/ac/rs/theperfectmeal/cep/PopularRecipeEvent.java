package com.ftn.uns.ac.rs.theperfectmeal.cep;

import java.time.Instant;
import java.util.Date;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import com.ftn.uns.ac.rs.theperfectmeal.model.Recipe;
import com.ftn.uns.ac.rs.theperfectmeal.model.RegisteredUser;

@Role(Role.Type.EVENT)
@Timestamp("timestamp")
@Expires("24h")
public class PopularRecipeEvent {

	private RegisteredUser user;
	private Recipe recipe;
	private Date timestamp;

	public PopularRecipeEvent() {
		super();
	}

	public PopularRecipeEvent(RegisteredUser user, Recipe recipe) {
		super();
		this.user = user;
		this.recipe = recipe;
		this.timestamp = Date.from(Instant.now());
	}

	public RegisteredUser getUser() {
		return user;
	}

	public void setUser(RegisteredUser user) {
		this.user = user;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

}
