package com.sky.SkyBankBackend.entities;

import com.sky.SkyBankBackend.DTO.PayeeDTO;
import com.sky.SkyBankBackend.DTO.TransactionDTO;
import com.sky.SkyBankBackend.rest.TransactionController;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;
    @Column(name = "date")
    private LocalDate transactionDate;
    private Double amountIn;
    private Double amountOut;
    private Integer payeeAccountNumber;
    private Integer payeeSortCode;

    @ManyToOne
    private Customer customer;

    public Transaction(String description, LocalDate transactionDate, Double amountIn, Double amountOut,
                       Customer customer, Integer payeeAccountNumber, Integer payeeSortCode) {
        this.description = description;
        this.transactionDate = transactionDate;
        this.amountIn = amountIn;
        this.amountOut = amountOut;
        this.customer = customer;
        this.payeeAccountNumber = payeeAccountNumber;
        this.payeeSortCode = payeeSortCode;
    }

    public Transaction(TransactionDTO newTransaction) {
        this.id = newTransaction.getId();
        this.description = newTransaction.getDescription();
        this.transactionDate = newTransaction.getTransactionDate();
        this.amountIn = newTransaction.getAmountIn();
        this.amountOut = newTransaction.getAmountOut();
        this.payeeAccountNumber = newTransaction.getPayeeAccountNumber();
        this.payeeSortCode = newTransaction.getPayeeSortCode();
        this.customer = new Customer();
        this.customer.setEmail(newTransaction.getCustomerEmail());
    }

    public Transaction() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Double getAmountIn() {
        return amountIn;
    }

    public void setAmountIn(Double amountIn) {
        this.amountIn = amountIn;
    }

    public Double getAmountOut() {
        return amountOut;
    }

    public void setAmountOut(Double amountOut) {
        this.amountOut = amountOut;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {this.customer = customer;
    }

    public Integer getPayeeAccountNumber() {
        return payeeAccountNumber;
    }

    public void setPayeeAccountNumber(Integer payeeAccountNumber) {
        this.payeeAccountNumber = payeeAccountNumber;
    }

    public Integer getPayeeSortCode() {
        return payeeSortCode;
    }

    public void setPayeeSortCode(Integer payeeSortCode) {
        this.payeeSortCode = payeeSortCode;
    }
}
