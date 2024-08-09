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
    private String email;
    private String sortCode;

    public Payee(String firstName, String lastName, String email, String sortCode){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.sortCode = sortCode;
    }
    // REQUIRED
    public Payee() {
        super();
    }

    public Payee(PayeeDTO newPayee) {
        this.id = newPayee.getId();
        this.firstName = newPayee.getFirstName();
        this.lastName = newPayee.getLastName();
        this.email = newPayee.getEmail();
        this.sortCode = newPayee.getSortCode();
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
