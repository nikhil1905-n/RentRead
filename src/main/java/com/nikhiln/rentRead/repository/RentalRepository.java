package com.nikhiln.rentRead.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nikhiln.rentRead.entity.Rental;

public interface RentalRepository extends JpaRepository<Rental, Long> {

    List<Rental> findByUserId(Long userId);

}
