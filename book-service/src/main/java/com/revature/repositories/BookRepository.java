package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{

	List<Book> findBooksByAuthorId(int authorId);
	
	void deleteBooksByAuthorId(int authorId);
}
