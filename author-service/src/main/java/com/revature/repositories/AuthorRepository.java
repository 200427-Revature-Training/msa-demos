package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.entities.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer>{

}
