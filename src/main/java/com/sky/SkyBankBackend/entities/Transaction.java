package com.sky.SkyBankBackend.entities;

import jakarta.persistence.*;

import java.util.Locale;

@Entity
@Table(name="Transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;
    private Locale data;
    private Integer in;
    private Integer out;
    private Integer balance;
    private String customerEmail;

    public Transaction(String description, Locale data, Integer in, Integer out, Integer balance, String customerEmail) {
        this.description = description;
        this.data = data;
        this.in = in;
        this.out = out;
        this.balance = balance;
        this.customerEmail = customerEmail;
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

    public Locale getData() {
        return data;
    }

    public void setData(Locale data) {
        this.data = data;
    }

    public Integer getIn() {
        return in;
    }

    public void setIn(Integer in) {
        this.in = in;
    }

    public Integer getOut() {
        return out;
    }

    public void setOut(Integer out) {
        this.out = out;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
}
