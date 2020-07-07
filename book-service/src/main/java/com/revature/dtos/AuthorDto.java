package com.revature.dtos;

import java.time.LocalDate;
import java.util.List;

import com.revature.entities.Book;

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
	
	public void setBirthDate(String birthDate) {
		this.birthDate = LocalDate.parse(birthDate);
	}

	public AuthorDto() {
		super();
	}

	public AuthorDto(int id, String name, LocalDate birthDate, List<Book> books) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}
	
	public AuthorDto(int id, String name, String birthDate, List<Book> books) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = LocalDate.parse(birthDate);
	}

	@Override
	public String toString() {
		return "AuthorDto [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuthorDto other = (AuthorDto) obj;
		if (birthDate == null) {
			if (other.birthDate != null)
				return false;
		} else if (!birthDate.equals(other.birthDate))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}