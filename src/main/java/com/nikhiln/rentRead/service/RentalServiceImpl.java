package com.nikhiln.rentRead.service;

import java.util.List;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.nikhiln.rentRead.entity.Book;
import com.nikhiln.rentRead.entity.Rental;
import com.nikhiln.rentRead.entity.User;
import com.nikhiln.rentRead.exception.BookUnavailableException;
import com.nikhiln.rentRead.exception.NotFoundException;
import com.nikhiln.rentRead.exception.RentalLimitExceededException;
import com.nikhiln.rentRead.repository.BookRepository;
import com.nikhiln.rentRead.repository.RentalRepository;
import com.nikhiln.rentRead.repository.UserRepository;

@Service
public class RentalServiceImpl implements RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void rentBook(Long bookId) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User not found"));

        List<Rental> userRentals = rentalRepository.findByUserId(user.getId());
        if(userRentals.size() >= 2) {
            throw new RentalLimitExceededException("Cannot have more than two active rentals");
        }

        Book book = bookRepository.findById(bookId).orElseThrow(() -> new NotFoundException("Book with ID " + bookId + " Not Found"));
        if(!book.isAvailable()) {
            throw new BookUnavailableException("Book is not available for rent");
        }

        book.setAvailable(false);
        bookRepository.save(book);

        Rental rental = new Rental();
        rental.setUser(user);
        rental.setBook(book);
        rental.setRentalDate(LocalDateTime.now());
        rentalRepository.save(rental);

    }

    @Override
    public void returnBook(Long bookId) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User not found"));

        Rental rental = rentalRepository.findByUserId(user.getId()).stream()
                .filter(r -> r.getBook().getId().equals(bookId)).findFirst()
                .orElseThrow(() -> new NotFoundException("Rental not found"));

        rentalRepository.delete(rental);

        Book book = rental.getBook();
        book.setAvailable(true);
        bookRepository.save(book);

    }
    
}
