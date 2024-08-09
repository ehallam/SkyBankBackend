package com.sky.SkyBankBackend.repositories;

import com.sky.SkyBankBackend.entities.Payee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PayeeRepo extends JpaRepository<Payee, Integer> {
}
