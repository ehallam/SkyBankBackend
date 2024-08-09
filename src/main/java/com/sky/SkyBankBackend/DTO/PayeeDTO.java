package com.sky.SkyBankBackend.DTO;

import com.sky.SkyBankBackend.entities.Payee;

public class PayeeDTO {

    private Integer id;

    private String firstName;
    private String lastName;
    private String email;
    private String sortCode;

    public PayeeDTO() {
    }

    public PayeeDTO(Payee payee) {
        this.id = payee.getId();
        this.firstName = payee.getFirstName();
        this.lastName = payee.getLastName();
        this.email = payee.getEmail();
        this.sortCode = payee.getSortCode();
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
