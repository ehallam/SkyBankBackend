package com.sky.SkyBankBackend.services;


import com.sky.SkyBankBackend.DTO.CustomerDTO;

import java.util.List;

public interface CustomerService {


    CustomerDTO getCustomer(int id);

    List<CustomerDTO> getAll();


    CustomerDTO getCustomerByEmail(String name);

    CustomerDTO addCustomer(CustomerDTO newCustomer);


    CustomerDTO remove(int id);
}
