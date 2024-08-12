package com.sky.SkyBankBackend.DTO;

import com.sky.SkyBankBackend.entities.Customer;
import com.sky.SkyBankBackend.entities.Payee;
import com.sky.SkyBankBackend.entities.Transaction;
import jakarta.persistence.Column;

import java.time.LocalDate;

public class TransactionDTO {

    private Integer id;

    private String description;
    private LocalDate transactionDate;
    private Double amountIn;
    private Double amountOut;
    private Integer payeeAccountNumber;
    private Integer payeeSortCode;
    private String customerEmail;


    public TransactionDTO() {
    }

    public TransactionDTO(Transaction transaction) {
        this.id = transaction.getId();
        this.description = transaction.getDescription();
        this.transactionDate = transaction.getTransactionDate();
        this.amountIn = transaction.getAmountIn();
        this.amountOut = transaction.getAmountOut();
        this.payeeAccountNumber = transaction.getPayeeAccountNumber();
        this.payeeSortCode = transaction.getPayeeSortCode();
        this.customerEmail = transaction.getCustomer().getEmail();
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

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {this.customerEmail = customerEmail;
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
