package com.sky.SkyBankBackend.services;

import com.sky.SkyBankBackend.DTO.TransactionDTO;
import com.sky.SkyBankBackend.entities.Customer;
import com.sky.SkyBankBackend.entities.Payee;
import com.sky.SkyBankBackend.entities.Transaction;
import com.sky.SkyBankBackend.exceptions.CustomerNotFoundException;
import com.sky.SkyBankBackend.exceptions.TransactionNotFoundException;
import com.sky.SkyBankBackend.repositories.CustomerRepo;
import com.sky.SkyBankBackend.repositories.TransactionRepo;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Primary
public class TransactionService {
    private TransactionRepo repo;

    private CustomerRepo customerRepo;

    public TransactionService(TransactionRepo repo, CustomerRepo customerRepo) {
        this.repo = repo;
        this.customerRepo = customerRepo;
    }

    public TransactionDTO addTransaction(TransactionDTO newTransaction) {
        Optional<Customer> payee = null;

        try{
            payee = this.customerRepo.findByAccountNumber(newTransaction.getPayeeAccountNumber());
        }
        catch(Exception ignored){
        }
        Customer customer = this.customerRepo.findById(newTransaction.getCustomerEmail()).orElseThrow(CustomerNotFoundException::new);

        Double customerInitialBalance = customer.getBalance();
        if(payee.isPresent()){
            Customer nPayee = payee.get();
            Double payeeInitialBalance = nPayee.getBalance();
            // The payee is receiving money
            if (newTransaction.getAmountOut() != null) {
                nPayee.setBalance(payeeInitialBalance + newTransaction.getAmountOut());
                customer.setBalance(customerInitialBalance - newTransaction.getAmountOut());
            } else {
                // The customer is getting money
                customer.setBalance(customerInitialBalance + newTransaction.getAmountIn());
                nPayee.setBalance(payeeInitialBalance - newTransaction.getAmountIn());
            }
            this.customerRepo.save(nPayee);
        }
        else{
            if (newTransaction.getAmountOut() != null) {
                customer.setBalance(customerInitialBalance - newTransaction.getAmountOut());
            } else {
                // The customer is getting money
                customer.setBalance(customerInitialBalance + newTransaction.getAmountIn());
            }
        }



        this.customerRepo.save(customer);

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
        List<Transaction> transactions = this.repo.findAllByCustomerEmailIgnoreCaseOrderByTransactionDateDesc(customerEmail).orElseThrow(TransactionNotFoundException::new);
        return transactions.stream().map(TransactionDTO::new).toList();
    }
}
