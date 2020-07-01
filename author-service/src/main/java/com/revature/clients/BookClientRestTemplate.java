package com.revature.clients;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.revature.dtos.BookDto;

/**
 * RestTemplate is a utility class provided by Spring which allows us
 * to send HTTP requests as a client to retrieve remote data. This tool
 * is quite flexible and powerful, but can be a little confusing as there
 * are a lot of methods on it.
 */
@Component
public class BookClientRestTemplate implements BookClient {

	@Override
	public BookDto[] getBooksByAuthorId(int authorId) {
		RestTemplate restTemplate = new RestTemplate();
		BookDto[] books = restTemplate.getForObject("http://localhost:8080/books?authorId="+authorId
				, BookDto[].class);
		
		return books;
	}

}
