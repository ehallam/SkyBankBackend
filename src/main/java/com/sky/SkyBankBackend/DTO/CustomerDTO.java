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
    private String sortCode;
    private int accountNumber;

    public CustomerDTO() {
    }

    public CustomerDTO(Customer customer) {
        this.id = customer.getId();
        this.firstName = customer.getFirstName();

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
