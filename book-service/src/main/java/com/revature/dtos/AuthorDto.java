package com.revature.dtos;

import java.time.LocalDate;

public class AuthorDto {
	
	private int id;
	
	private String name;
	
	private LocalDate birthDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public AuthorDto() {
		super();
	}
}