package com.nikhiln.rentRead.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nikhiln.rentRead.dto.BookDto;
import com.nikhiln.rentRead.entity.Book;
import com.nikhiln.rentRead.exception.NotFoundException;
import com.nikhiln.rentRead.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<BookDto> getAllBooks() {
        // List<Book> books = bookRepository.findAll();
        // return books.stream().map(book -> modelMapper.map(book, BookDto.class)).collect(Collectors.toList());

        // OR
        return bookRepository.findAll().stream().map(book -> { 
            BookDto bookDto = new BookDto();
            bookDto.setId(book.getId()); 
            bookDto.setTitle(book.getTitle());
            bookDto.setAuthor(book.getAuthor());
            bookDto.setGenre(book.getGenre());
            bookDto.setAvailable(book.isAvailable()); 
            return bookDto; 
        }).collect(Collectors.toList());


    }

    @Override
    public BookDto getBookById(Long bookId) {

        // Book book = bookRepository.findById(bookId).orElseThrow(() -> new NotFoundException("Book with ID " + bookId + " Not Found"));

        // OR

        Optional<Book> book = bookRepository.findById(bookId);

        if(book.isEmpty()) {
            throw new NotFoundException("Book with ID " + bookId + " Not Found");
        }

        return modelMapper.map(book, BookDto.class);

    }

    @Override
    public BookDto createBook(BookDto bookDto) {

        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setGenre(bookDto.getGenre());
        book.setAvailable(true);

        book = bookRepository.save(book);

        bookDto.setId(book.getId());

        return bookDto;
    }

    @Override
    public BookDto updateBook(Long bookId, BookDto bookDto) {

        Book book = bookRepository.findById(bookId).orElseThrow(() -> new NotFoundException("Book with ID " + bookId + " Not Found"));

        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setGenre(bookDto.getGenre());
        book.setAvailable(true);

        book = bookRepository.save(book);

        bookDto.setId(book.getId());

        return bookDto;

    }

    @Override
    public void deleteBook(Long bookId) {

        // Book book = bookRepository.findById(bookId).orElseThrow(() -> new NotFoundException("Book with ID " + bookId + " Not Found"));

        // OR

        Optional<Book> book = bookRepository.findById(bookId);

        if(book.isEmpty()) {
            throw new NotFoundException("Book with ID " + bookId + " Not Found");
        }

        bookRepository.deleteById(bookId);

    }
    
}
