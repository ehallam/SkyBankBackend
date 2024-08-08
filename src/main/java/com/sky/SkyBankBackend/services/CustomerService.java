package com.sky.SkyBankBackend.services;

import com.sky.SkyBankBackend.DTO.CustomerDTO;
import com.sky.SkyBankBackend.entities.Customer;
import com.sky.SkyBankBackend.exceptions.CustomerNotFoundException;

import java.util.List;

public interface CustomerService {
    CustomerDTO addCustomer(CustomerDTO newCustomer);

    public CustomerDTO getCustomer(int id);

    public List<CustomerDTO> getAll();

    public CustomerDTO getCustomerByEmail(String email);

    public CustomerDTO remove(int id);
}
