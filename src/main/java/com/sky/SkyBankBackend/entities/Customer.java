package com.sky.SkyBankBackend.entities;

import com.sky.SkyBankBackend.DTO.CustomerDTO;
import jakarta.persistence.*;

@Entity // marks the class as a table
@Table(name = "customer") // lets you configure the created table
public class Customer {

    @Id // PK
    private String email;

    @Column
    private String firstName;
    private String lastName;

    private String password;
    private Integer sortCode;
    private Integer accountNumber;
    private Double balance;

    public Customer(String firstName, String lastName, String email, String password, Integer sortCode, Integer accountNumber, Double balance){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.sortCode = sortCode;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
    // REQUIRED
    public Customer() {
        super();
    }

    public Customer(CustomerDTO newCustomer) {
        this.email = newCustomer.getEmail();
        this.firstName = newCustomer.getFirstName();
        this.lastName = newCustomer.getLastName();
        this.password = newCustomer.getPassword();
        this.sortCode = newCustomer.getSortCode();
        this.accountNumber = newCustomer.getAccountNumber();
        this.balance = newCustomer.getBalance();
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

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber){
        this.accountNumber = accountNumber;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return this.password;
    }

    public Double getBalance(){
        return balance;
    }

    public void setBalance(Double balance){
        this.balance = balance;
    }

    public Integer getSortCode(){
        return this.sortCode;
    }

    public void setSortCode(Integer sortCode) {
        this.sortCode = sortCode;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
