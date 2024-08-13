package com.sky.SkyBankBackend.services;


import com.sky.SkyBankBackend.DTO.CustomerDTO;
import com.sky.SkyBankBackend.DTO.LoginRequestDTO;
import com.sky.SkyBankBackend.DTO.TransactionDTO;
import com.sky.SkyBankBackend.entities.Customer;
import com.sky.SkyBankBackend.entities.Transaction;
import com.sky.SkyBankBackend.exceptions.CustomerNotFoundException;
import com.sky.SkyBankBackend.repositories.CustomerRepo;
import com.sky.SkyBankBackend.repositories.TransactionRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Primary // tells spring to use this one
public class CustomerServiceDB implements CustomerService {
    private final CustomerRepo repo;
    private PasswordEncoder passwordEncoder;
    private TransactionRepo tRepo;

    public CustomerServiceDB(CustomerRepo repo, TransactionRepo tRepo, PasswordEncoder passwordEncoder) {
       this.repo = repo;
        this.tRepo = tRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public CustomerDTO addCustomer(CustomerDTO newCustomer) {
        var badReq = this.repo.findByEmailIgnoreCase(newCustomer.getEmail());
        badReq.ifPresent(data -> {
            throw new EntityNotFoundException("Customer with email "+newCustomer.getEmail()+" already exists");
        });
        String hash = this.passwordEncoder.encode(newCustomer.getPassword());
        newCustomer.setPassword(hash);
        Integer newAccountNo = null;
        Random rand = new Random();
        do {
            newAccountNo = rand.nextInt(10000000, 99999999);
        } while (this.repo.findByAccountNumber(newAccountNo).isPresent());
        Integer newSortCode = null;
        do {
            newSortCode = rand.nextInt(100000, 999999);
        } while (this.repo.findBySortCode(newSortCode).isPresent());
        newCustomer.setSortCode(newSortCode);
        newCustomer.setAccountNumber(newAccountNo);
        newCustomer.setBalance(500);
        Customer toSave = new Customer(newCustomer);
        Customer created = this.repo.save(toSave);
        Transaction tSave = new Transaction("Welcome Gift", null, 500.0, 0.0, created, 11111111, 111111);
        Transaction tCreated = this.tRepo.save(tSave);
        return new CustomerDTO(created);
    }

    @Override
    public List<CustomerDTO> getAll() {
        return this.repo.findAll().stream().map(CustomerDTO::new).toList();
    }

    @Override
    public CustomerDTO getCustomerByEmail(String email) {
        Customer found = this.repo.findById(email).orElseThrow(CustomerNotFoundException::new);
        return new CustomerDTO(found);
    }

    @Override
    public CustomerDTO remove(String email) {
        Customer found = this.repo.findById(email).orElseThrow(CustomerNotFoundException::new);
        this.repo.deleteById(email);
        return new CustomerDTO(found);
    }
}
