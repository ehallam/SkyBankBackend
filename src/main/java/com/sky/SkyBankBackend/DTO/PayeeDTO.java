package com.sky.SkyBankBackend.DTO;

import com.sky.SkyBankBackend.entities.Payee;

public class PayeeDTO {

    private Integer id;

    private String firstName;
    private String lastName;
    private Integer sortCode;
    private String customerEmail;
    private Integer accountNumber;

    public PayeeDTO() {
    }

    public PayeeDTO(Payee payee) {
        this.id = payee.getId();
        this.firstName = payee.getFirstName();
        this.lastName = payee.getLastName();
        this.customerEmail = payee.getCustomer().getEmail();
        this.sortCode = payee.getSortCode();
        this.accountNumber = payee.getAccountNumber();
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

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() { return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getAccountNumber(){
        return this.accountNumber;
    }

    public void setAccountNumber(Integer accountNumber){
        this.accountNumber = accountNumber;
    }


    public Integer getSortCode(){
        return this.sortCode;
    }

    public void setSortCode(Integer sortCode) {
        this.sortCode = sortCode;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

}
