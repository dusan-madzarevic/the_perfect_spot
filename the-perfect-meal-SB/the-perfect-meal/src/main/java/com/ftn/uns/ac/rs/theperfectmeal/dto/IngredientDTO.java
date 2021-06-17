package com.ftn.uns.ac.rs.theperfectmeal.dto;

public class IngredientDTO {

	private long id;

	private String name;

	public IngredientDTO() {
		super();
	}

	public IngredientDTO(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
