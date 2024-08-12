package com.sky.SkyBankBackend.rest;

import com.sky.SkyBankBackend.DTO.CustomerDTO;
import com.sky.SkyBankBackend.DTO.LoginRequestDTO;
import com.sky.SkyBankBackend.entities.Customer;
import com.sky.SkyBankBackend.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@CrossOrigin
public class CustomerController {


    private CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!"; // returned values are stored in response body
    }

    @GetMapping("/get/email/{email}")
    public CustomerDTO getCustomerByEmail(@PathVariable String email) {
        return this.service.getCustomerByEmail(email);
    }

    @GetMapping("/get/accountNumber/{accountNumber}")
    public CustomerDTO getCustomerByAccountNumber(@PathVariable Integer accountNumber) {
        return this.service.getCustomerByAccountNumber(accountNumber);
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

    @DeleteMapping("/remove/{email}")
    public CustomerDTO remove(@PathVariable String email) {
        return this.service.remove(email);
    }

    @PutMapping("/update/{email}")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO update(@PathVariable String email, @RequestBody Customer newCustomer) {
        return this.service.update(email, newCustomer);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO newLoginRequest) {
        return this.service.login(newLoginRequest);
    }
}
