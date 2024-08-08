package com.sky.SkyBankBackend.entities;

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

    public Customer(String firstName, String lastName, String email, String password, String sortCode, int accountNumber){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.sortCode = sortCode;
        this.accountNumber = accountNumber;
    }
    // REQUIRED
    public Customer() {
        super();
    }


    // REQUIRED
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return lastName;
    }

    public void setName(String name) {
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

    public void setEmail(String email) {
        this.email = email;
    }

    public void getEmail(String email){
        this.email=email;
    }


}
