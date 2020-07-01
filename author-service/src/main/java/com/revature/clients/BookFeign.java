package com.revature.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.revature.dtos.BookDto;

@Primary
@FeignClient(name="book-service", url="http://localhost:8010", fallback=BookFeignFallback.class)
public interface BookFeign extends BookClient{
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public BookDto createBookDto(@RequestBody BookDto book);

	@GetMapping("/{id}")
	public BookDto getBookDtoById(@PathVariable int id);

	@GetMapping
	public BookDto[] getBooksByAuthorId(@RequestParam(required = false) int authorId);

	@PutMapping
	public BookDto updateBookDto(@RequestBody BookDto book);
}
