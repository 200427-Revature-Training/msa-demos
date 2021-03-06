package com.revature.services;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.revature.clients.BookClient;
import com.revature.dtos.BookDto;
import com.revature.entities.Author;
import com.revature.messengers.AuthorMessenger;
import com.revature.repositories.AuthorRepository;

@Service
public class AuthorService {

	@Autowired
	AuthorRepository authorRepository;
	
	@Autowired
	BookClient bookClient;
	
	@Autowired
	AuthorMessenger authorMessenger;

	public Author create(Author author) {
		return authorRepository.save(author);
	}

	public Author getAuthorbyId(int id) {
		Author author = authorRepository.findById(id).orElseThrow(
				() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
		
		// Retrieve books that were written by this Author
		BookDto[] booksArr = bookClient.getBooksByAuthorId(id);		
		
		if (booksArr != null) {
			List<BookDto> books = Arrays.asList(booksArr);
			author.setBooks(books);
		}
		
		return author;
	}

	public List<Author> getAuthors() {
		return authorRepository.findAll();
	}

	@Transactional
	public Author deleteAuthor(int id) {
		Author author = authorRepository.findById(id)
				.orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
		System.out.println(author);
		
		authorRepository.delete(author);
		System.out.println(author);
		// Send message to SNS
		authorMessenger.sendAuthorDeletionMessage(author);
		
		return author;
	}
}
