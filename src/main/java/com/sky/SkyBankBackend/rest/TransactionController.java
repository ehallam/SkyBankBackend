package com.sky.SkyBankBackend.rest;

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
    public List<Transaction> getAllTransactions() {
        return this.service.getAll();
    }

    @GetMapping("/get/{id}")
    public Transaction getTransaction(@PathVariable int id) {
        return this.service.getTransaction(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Transaction addTransaction(@RequestBody Transaction transaction) {
        return this.service.addTransaction(transaction);
    }

    @PutMapping("/update/{id}")
    public Transaction updateTransaction(@RequestBody Transaction transaction, @PathVariable int id) {
        return this.service.updateTransaction(id, transaction);
    }

    @DeleteMapping("/delete/{id}")
    public Transaction deleteById(@PathVariable int id) {
        return this.service.delete(id);
    }
}
