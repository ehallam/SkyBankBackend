package com.sky.SkyBankBackend.rest;

import com.sky.SkyBankBackend.DTO.CustomerDTO;
import com.sky.SkyBankBackend.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {


    private CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!"; // returned values are stored in response body
    }

    @GetMapping("/get/{id}")
    public CustomerDTO getCustomer(@PathVariable int id) {
        return this.service.getCustomer(id);
    }

    @GetMapping("/get/email/{email}")
    public CustomerDTO getCustomerByEmail(@PathVariable String email) {
        return this.service.getCustomerByEmail(email);
    }

    @GetMapping("/getAll")
    public List<CustomerDTO> getAll() {
        return this.service.getAll();
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public CustomerDTO addCustomer(@RequestBody CustomerDTO newCustomer) {
        return this.service.addCustomer(newCustomer);
    }


    @DeleteMapping("/remove/{id}")
    public CustomerDTO remove(@PathVariable int id) {
        return this.service.remove(id);
    }
}
