package com.nikhiln.rentRead.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nikhiln.rentRead.service.RentalService;

@RestController
@RequestMapping("/rentals")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @PostMapping("/rent/{bookId}")
    public ResponseEntity<String> rentBook(@PathVariable Long bookId) {
        
        rentalService.rentBook(bookId);
        return ResponseEntity.ok("Book rented successfully.");

    }

    @PostMapping("/return/{bookId}")
    public ResponseEntity<String> returnBook(@PathVariable Long bookId) {
        
        rentalService.returnBook(bookId);
        return ResponseEntity.ok("Book returned successfully.");

    }
    
}
