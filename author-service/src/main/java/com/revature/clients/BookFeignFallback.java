package com.revature.clients;

import org.springframework.stereotype.Component;

import com.revature.dtos.BookDto;

@Component
public class BookFeignFallback implements BookFeign {

	@Override
	public BookDto createBookDto(BookDto book) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BookDto getBookDtoById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BookDto[] getBooksByAuthorId(int authorId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BookDto updateBookDto(BookDto book) {
		// TODO Auto-generated method stub
		return null;
	}

}
