package com.sky.SkyBankBackend.entities;

import com.sky.SkyBankBackend.DTO.PayeeDTO;
import jakarta.persistence.*;

@Entity // marks the class as a table
@Table(name = "payee") // lets you configure the created table
public class Payee {

    @Id // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Integer id;

    @Column
    private String firstName;
    private String lastName;

    private Integer sortCode;
    private Integer accountNumber;

    @ManyToOne(cascade=CascadeType.ALL)
    private Customer customer;

    public Payee(String firstName, String lastName, String customerEmail , Integer sortCode, Integer accountNumber, Customer customer){
        this.firstName = firstName;
        this.lastName = lastName;
        this.sortCode = sortCode;
        this.accountNumber = accountNumber;
        this.customer = customer;
    }
    // REQUIRED
    public Payee() {
        super();
    }

    public Payee(PayeeDTO newPayee) {
        this.id = newPayee.getId();
        this.firstName = newPayee.getFirstName();
        this.lastName = newPayee.getLastName();
        this.sortCode = newPayee.getSortCode();
        this.accountNumber = newPayee.getAccountNumber();
        this.customer = new Customer();
        this.customer.setEmail(newPayee.getCustomerEmail());
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

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() { return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getSortCode(){
        return this.sortCode;
    }

    public void setSortCode(Integer sortCode) {
        this.sortCode = sortCode;
    }


    public Integer getAccountNumber(){
        return this.accountNumber;
    }

    public void setAccountNumber(Integer accountNumber){
        this.accountNumber = accountNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
