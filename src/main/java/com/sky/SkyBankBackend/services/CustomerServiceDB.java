package com.sky.SkyBankBackend.services;


import com.sky.SkyBankBackend.DTO.CustomerDTO;
import com.sky.SkyBankBackend.entities.Customer;
import com.sky.SkyBankBackend.exceptions.CustomerNotFoundException;
import com.sky.SkyBankBackend.repositories.CustomerRepo;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary // tells spring to use this one
public class CustomerServiceDB implements CustomerService {
    private final CustomerRepo repo;

    public CustomerServiceDB(CustomerRepo repo) {
        this.repo = repo;
    }

    @Override
    public CustomerDTO addCustomer(CustomerDTO newCustomer) {
        Customer toSave = new Customer(newCustomer);
        Customer created = this.repo.save(toSave);
        return new CustomerDTO(created);
    }

    @Override
    public CustomerDTO getCustomer(int id) {
        Customer found = this.repo.findById(id).orElseThrow(CustomerNotFoundException::new);
        return new CustomerDTO(found);
    }

    @Override
    public List<CustomerDTO> getAll() {
        return this.repo.findAll().stream().map(CustomerDTO::new).toList();
    }

    @Override
    public CustomerDTO getCustomerByEmail(String email) {
        Customer found = this.repo.findByEmailIgnoreCase(email).orElseThrow(CustomerNotFoundException::new);
        return new CustomerDTO(found);
    }

    @Override
    public CustomerDTO remove(int id) {
        Customer found = this.repo.findById(id).orElseThrow(CustomerNotFoundException::new);
        this.repo.deleteById(id);
        return new CustomerDTO(found);
    }
}
