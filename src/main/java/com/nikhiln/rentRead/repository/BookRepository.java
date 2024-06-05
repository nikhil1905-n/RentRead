package com.nikhiln.rentRead.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nikhiln.rentRead.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
