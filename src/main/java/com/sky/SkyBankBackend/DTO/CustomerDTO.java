package com.sky.SkyBankBackend.DTO;

import com.sky.SkyBankBackend.entities.Customer;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;

public class CustomerDTO {

    private Integer id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int sortCode;
    private int accountNumber;
    private double balance;

    public CustomerDTO() {
    }

    public CustomerDTO(Customer customer) {
        this.id = customer.getId();
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.email = customer.getEmail();
        this.password = customer.getPassword();
        this.sortCode = customer.getSortCode();
        this.accountNumber = customer.getAccountNumber();
        this.balance = customer.getBalance();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() { return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber){
        this.accountNumber = accountNumber;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return this.password;
    }

    public int getSortCode(){
        return this.sortCode;
    }

    public void setSortCode(int sortCode) {
        this.sortCode = sortCode;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getBalance(){
        return this.balance;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }
}
