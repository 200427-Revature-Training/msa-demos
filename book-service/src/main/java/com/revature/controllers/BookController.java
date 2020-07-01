package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.revature.entities.Book;
import com.revature.services.BookService;

@RestController
public class BookController {

	@Autowired
	BookService bookService;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Book createBook(@RequestBody Book book) {
		return bookService.createBook(book);
	}

	@GetMapping("/{id}")
	public Book getBookById(@PathVariable int id) {
		return bookService.getBookById(id);
	}
	
	
	
	@GetMapping
	public List<Book> getBooks(
			@RequestParam(required=false) Integer authorId) {
		
		if(authorId != null) {
			return getBooksbyAuthorId(authorId);
		} else {
			return getAllBooks();
		}
	}
	
	@PutMapping
	public Book updateBook(@RequestBody Book book) {
		return bookService.updateBook(book);
	}

	private List<Book> getBooksbyAuthorId(Integer authorId) {
		return bookService.getBooksByAuthorId(authorId);
	}

	private List<Book> getAllBooks() {
		return bookService.getAllBooks();
	}

	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<String> handleHttpClientException(HttpClientErrorException e) {
		return ResponseEntity
					.status(e.getStatusCode())
					.body(e.getMessage());
	}
}
