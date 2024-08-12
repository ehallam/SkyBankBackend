package com.sky.SkyBankBackend.services;

import com.sky.SkyBankBackend.DTO.TransactionDTO;
import com.sky.SkyBankBackend.entities.Transaction;
import com.sky.SkyBankBackend.exceptions.CustomerNotFoundException;
import com.sky.SkyBankBackend.exceptions.TransactionNotFoundException;
import com.sky.SkyBankBackend.repositories.TransactionRepo;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Primary
public class TransactionService {
    private TransactionRepo repo;

    public TransactionService(TransactionRepo repo) {
        this.repo = repo;
    }

    
    public TransactionDTO addTransaction(TransactionDTO newTransaction) {
        Transaction toSave = new Transaction(newTransaction);
        Transaction created = this.repo.save(toSave);
        return new TransactionDTO(created);
    }

    
    public TransactionDTO getTransaction(int id) {
        Transaction found = this.repo.findById(id).orElseThrow(TransactionNotFoundException::new);
        return new TransactionDTO(found);
    }

    
    public List<TransactionDTO> getAll() {
        return this.repo.findAll().stream().map(TransactionDTO::new).toList();
    }


    public TransactionDTO updateTransaction(Integer id, TransactionDTO transaction) {
        Transaction toUpdate = this.repo.findById(id).orElseThrow(TransactionNotFoundException::new);
        String description = transaction.getDescription();
        LocalDate transactionDate = transaction.getTransactionDate();
        Double amountIn = transaction.getAmountIn();
        Double amountOut = transaction.getAmountOut();


        if (description != null) toUpdate.setDescription(description);
        if (transactionDate != null) toUpdate.setTransactionDate(transactionDate);
        if (amountIn != null) toUpdate.setAmountIn(amountIn);
        if (amountOut != null) toUpdate.setAmountOut(amountOut);

        return new TransactionDTO(this.repo.save(toUpdate));
    }

    public TransactionDTO delete(Integer id) {
        Transaction toDelete = this.repo.findById(id).orElseThrow(TransactionNotFoundException::new);
        this.repo.deleteById(id);
        return new TransactionDTO(toDelete);
    }

    public List<TransactionDTO> getAllByEmail(String customerEmail) {
        List<Transaction> transactions = this.repo.findAllByCustomerEmailIgnoreCase(customerEmail).orElseThrow(TransactionNotFoundException::new);
        return transactions.stream().map(TransactionDTO::new).toList();
    }
}
