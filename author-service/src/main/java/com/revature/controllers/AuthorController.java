package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.entities.Author;
import com.revature.services.AuthorService;

@RestController
public class AuthorController {

	@Autowired
	AuthorService authorService;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Author createAuthor(@RequestBody Author author) {
		return authorService.create(author);
	}
}
