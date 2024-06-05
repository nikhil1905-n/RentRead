package com.nikhiln.rentRead.service;

import java.util.List;

import com.nikhiln.rentRead.dto.BookDto;

public interface BookService {

    List<BookDto> getAllBooks();
    BookDto getBookById(Long bookId);
    BookDto createBook(BookDto bookDto);
    BookDto updateBook(Long bookId, BookDto bookDto);
    void deleteBook(Long bookId);
    
}
