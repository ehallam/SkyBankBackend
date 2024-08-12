package com.sky.SkyBankBackend.services;

import com.sky.SkyBankBackend.DTO.CustomerDTO;
import com.sky.SkyBankBackend.DTO.LoginRequestDTO;
import com.sky.SkyBankBackend.entities.Customer;
import com.sky.SkyBankBackend.exceptions.CustomerNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {
    CustomerDTO addCustomer(CustomerDTO newCustomer);

    public List<CustomerDTO> getAll();

    public CustomerDTO getCustomerByEmail(String email);

    public CustomerDTO remove(String email);

    public ResponseEntity<?> login(LoginRequestDTO newLoginRequestDTO);
}
