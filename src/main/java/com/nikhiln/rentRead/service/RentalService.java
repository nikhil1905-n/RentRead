package com.nikhiln.rentRead.service;

public interface RentalService {

    void rentBook(Long bookId);
    void returnBook(Long bookId);
    
}
