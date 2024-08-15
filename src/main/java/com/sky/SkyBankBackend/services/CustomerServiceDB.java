package com.sky.SkyBankBackend.services;


import com.sky.SkyBankBackend.DTO.CustomerDTO;
import com.sky.SkyBankBackend.DTO.LoginRequestDTO;
import com.sky.SkyBankBackend.DTO.TransactionDTO;
import com.sky.SkyBankBackend.entities.Customer;
import com.sky.SkyBankBackend.entities.Transaction;
import com.sky.SkyBankBackend.exceptions.CustomerNotFoundException;
import com.sky.SkyBankBackend.exceptions.DuplicateEmailException;
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
import java.time.LocalDate;
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
            throw new DuplicateEmailException();
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
        newCustomer.setBalance(500.00);
        Customer toSave = new Customer(newCustomer);
        Customer created = this.repo.save(toSave);
        Transaction tSave = new Transaction("Welcome Gift", LocalDate.now(), 500.0, 0.0, created, 11111111, 111111);
        Transaction tCreated = this.tRepo.save(tSave);
        return new CustomerDTO(created);
    }

    @Override
    public List<CustomerDTO> getAll() {
        List<CustomerDTO> customers = this.repo.findAll().stream().map(CustomerDTO::new).toList();
        customers.forEach(c -> c.setPassword(null));
        return customers;
    }

    @Override
    public CustomerDTO getCustomerByEmail(String email) {
        Customer found = this.repo.findById(email).orElseThrow(CustomerNotFoundException::new);
        found.setPassword(null);
        return new CustomerDTO(found);
    }

    @Override
    public CustomerDTO getCustomerByAccountNumber(Integer accountNumber) {
        Customer found = this.repo.findByAccountNumber(accountNumber).orElseThrow(CustomerNotFoundException::new);
        found.setPassword(null);
        return new CustomerDTO(found);
    }

    @Override
    public CustomerDTO remove(String email) {
        Customer found = this.repo.findById(email).orElseThrow(CustomerNotFoundException::new);
        this.repo.deleteById(email);
        return new CustomerDTO(found);
    }

    @Override
    public CustomerDTO update(String email, Customer newCustomer) {
        Customer toUpdate = this.repo.findById(email).orElseThrow(CustomerNotFoundException::new);

        String firstName = newCustomer.getFirstName();
        String lastName = newCustomer.getLastName();
        String customerEmail = newCustomer.getEmail();
        Integer sortCode = newCustomer.getSortCode();
        Integer accountNumber = newCustomer.getAccountNumber();
        Double balance = newCustomer.getBalance();

        if (firstName != null) toUpdate.setFirstName(firstName);
        if (lastName != null) toUpdate.setLastName(lastName);
        if (customerEmail != null) toUpdate.setEmail(customerEmail);
        if (sortCode != null) toUpdate.setSortCode(sortCode);
        if (accountNumber != null) toUpdate.setAccountNumber(accountNumber);
        if (balance != null) toUpdate.setBalance(balance);

        return new CustomerDTO(this.repo.save(toUpdate));
    }
}
