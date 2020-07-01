package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.revature.entities.Book;
import com.revature.repositories.BookRepository;

@Service
public class BookService {

	@Autowired
	BookRepository bookRepository;

	public Book createBook(Book book) {
		return bookRepository.save(book);
	}

	public Book getBookById(int id) {
		return bookRepository.findById(id).orElseThrow(() ->
			new HttpClientErrorException(HttpStatus.NOT_FOUND));
	}

	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	public List<Book> getBooksByAuthorId(Integer authorId) {
		return bookRepository.findBooksByAuthorId(authorId);
	}

	public Book updateBook(Book book) {
		return bookRepository.save(book);
	}
	
}
