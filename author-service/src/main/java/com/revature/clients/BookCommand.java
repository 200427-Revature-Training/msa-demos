package com.revature.clients;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.revature.dtos.BookDto;

@Component
public class BookCommand implements BookClient {

	public BookDto[] getBooksFallback(int authorId) {
		return null;
	}

	@Override
	@HystrixCommand(fallbackMethod = "getBooksFallback")
	public BookDto[] getBooksByAuthorId(int authorId) {
		RestTemplate restTemplate = new RestTemplate();
		BookDto[] books = restTemplate.getForObject("http://localhost:8080/books?authorId="+authorId
				, BookDto[].class);
		
		return books;
	}
	
}
