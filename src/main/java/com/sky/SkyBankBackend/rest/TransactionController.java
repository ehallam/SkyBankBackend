package com.sky.SkyBankBackend.rest;

import com.sky.SkyBankBackend.DTO.PayeeDTO;
import com.sky.SkyBankBackend.DTO.TransactionDTO;
import com.sky.SkyBankBackend.entities.Transaction;
import com.sky.SkyBankBackend.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.List;

@RestController
@RequestMapping("/transaction")
@CrossOrigin
public class TransactionController {
    public TransactionController() {}
    
    @Autowired
    private TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @GetMapping("/getAll")
    public List<TransactionDTO> getAllTransactions() {
        return this.service.getAll();
    }

    @GetMapping("/get/{id}")
    public TransactionDTO getTransaction(@PathVariable int id) {
        return this.service.getTransaction(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionDTO addTransaction(@RequestBody TransactionDTO transaction) {
        return this.service.addTransaction(transaction);

    }

    @GetMapping("/getAll/{email}/{accountNumber}")
    public List<TransactionDTO> getAllByEmailOrAccountNumber(@PathVariable String email, @PathVariable Integer accountNumber) {
        return this.service.getAllByEmailOrAccountNumber(email,accountNumber);
    }
}
