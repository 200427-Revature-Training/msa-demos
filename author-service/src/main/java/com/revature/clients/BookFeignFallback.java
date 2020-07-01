package com.revature.clients;

import com.revature.dtos.BookDto;

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
