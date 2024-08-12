package com.sky.SkyBankBackend.services;

import com.sky.SkyBankBackend.entities.Transaction;
import com.sky.SkyBankBackend.exceptions.TransactionNotFoundException;
import com.sky.SkyBankBackend.repositories.TransactionRepo;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@Primary
public class TransactionService {
    private TransactionRepo repo;

    public TransactionService(TransactionRepo repo) {
        this.repo = repo;
    }

    public Transaction addTransaction(Transaction newTransaction) {
        return this.repo.save(newTransaction);
    }

    public Transaction getTransaction(Integer id) {
        return this.repo.findById(id).orElseThrow(TransactionNotFoundException::new);
    }

    public List<Transaction> getAll() {
        return this.repo.findAll();
    }

    public Transaction updateTransaction(Integer id, Transaction transaction) {
        Transaction toUpdate = this.repo.findById(id).orElseThrow(TransactionNotFoundException::new);
        String description = transaction.getDescription();
        LocalDate transactionDate = transaction.getTransactionDate();
        Double amountIn = transaction.getAmountIn();
        Double amountOut = transaction.getAmountOut();
        Double balance = transaction.getBalance();
        String customerEmail = transaction.getCustomerEmail();


        if (description != null) toUpdate.setDescription(description);
        if (transactionDate != null) toUpdate.setTransactionDate(transactionDate);
        if (amountIn != null) toUpdate.setAmountIn(amountIn);
        if (amountOut != null) toUpdate.setAmountOut(amountOut);
        if (balance != null) toUpdate.setBalance(balance);
        if (customerEmail != null) toUpdate.setCustomerEmail(customerEmail);

        return this.repo.save(toUpdate);
    }

    public Transaction delete(Integer id) {
        Transaction toDelete = this.repo.findById(id).orElseThrow(TransactionNotFoundException::new);
        this.repo.deleteById(id);
        return toDelete;
    }
}
