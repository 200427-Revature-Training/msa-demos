package com.revature.clients;

import com.revature.dtos.BookDto;

public interface BookClient {
	BookDto[] getBooksByAuthorId(int authorId);
}
