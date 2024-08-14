package com.sky.SkyBankBackend.repositories;

import com.sky.SkyBankBackend.entities.Payee;
import com.sky.SkyBankBackend.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepo extends JpaRepository<Transaction, Integer> {
    Optional<List<Transaction>> findAllByCustomerEmailIgnoreCase(String email);

}
