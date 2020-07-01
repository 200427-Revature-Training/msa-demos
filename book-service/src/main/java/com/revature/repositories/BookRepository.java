package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{

}
