package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

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

	@GetMapping("/{id}")
	public Author getAuthorById(@PathVariable int id) {
		return authorService.getAuthorbyId(id);
	}

	@GetMapping
	public List<Author> getAuthors() {
		return authorService.getAuthors();
	}
	
	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<String> handleHttpClientException(HttpClientErrorException e) {
		return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
	}
	
	@DeleteMapping("/{id}")
	public Author deleteAuthor(@PathVariable int id) {
		return authorService.deleteAuthor(id);
	}
}
