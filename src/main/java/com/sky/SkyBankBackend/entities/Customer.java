package com.sky.SkyBankBackend.entities;

import com.sky.SkyBankBackend.DTO.CustomerDTO;
import jakarta.persistence.*;

@Entity // marks the class as a table
@Table(name = "customer") // lets you configure the created table
public class Customer {

    @Id // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Integer id;

    @Column
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String sortCode;
    private int accountNumber;
    private double balance;

    public Customer(String firstName, String lastName, String email, String password, String sortCode, int accountNumber, double balance){
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
        this.id = newCustomer.getId();
        this.firstName = newCustomer.getFirstName();
        this.lastName = newCustomer.getLastName();
        this.email = newCustomer.getEmail();
        this.password = newCustomer.getPassword();
        this.sortCode = newCustomer.getSortCode();
        this.accountNumber = newCustomer.getAccountNumber();
        this.balance = newCustomer.getBalance();
    }




    // REQUIRED
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

    public double getBalance(){
        return this.balance;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }

    public String getSortCode(){
        return this.sortCode;
    }

    public void setSortCode(String sortCode) {
        this.sortCode = sortCode;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
