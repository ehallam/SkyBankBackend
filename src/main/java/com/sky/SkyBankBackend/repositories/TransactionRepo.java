package com.sky.SkyBankBackend.repositories;

import com.sky.SkyBankBackend.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepo extends JpaRepository<Transaction, Integer> {
}
