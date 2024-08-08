package com.sky.SkyBankBackend.repositories;

import com.sky.SkyBankBackend.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByEmailIgnoreCase(String email);
}
