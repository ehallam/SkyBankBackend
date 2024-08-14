package com.sky.SkyBankBackend.rest;

import com.sky.SkyBankBackend.DTO.CustomerDTO;
import com.sky.SkyBankBackend.DTO.PayeeDTO;
import com.sky.SkyBankBackend.entities.Payee;
import com.sky.SkyBankBackend.services.PayeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payee")
@CrossOrigin
public class PayeeController {


    private PayeeService service;

    public PayeeController(PayeeService service) {
        this.service = service;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!"; // returned values are stored in response body
    }

    @GetMapping("/get/{id}")
    public PayeeDTO getPayee(@PathVariable int id) {
        return this.service.getPayee(id);
    }

    @GetMapping("/getAll")
    public List<PayeeDTO> getAll() {
        return this.service.getAll();
    }

    @GetMapping("/getAll/{email}")
    public List<PayeeDTO> getAllByEmail(@PathVariable String email) {
        return this.service.getAllByEmail(email);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public PayeeDTO addPayee(@RequestBody PayeeDTO newPayee) {
        return this.service.addPayee(newPayee);
    }


    @DeleteMapping("/remove/{accountNumber}/{email}")
    public PayeeDTO remove(@PathVariable Integer accountNumber, @PathVariable String email) {
        return this.service.remove(accountNumber,email);
    }
}
