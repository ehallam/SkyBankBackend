package com.sky.SkyBankBackend.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sky.SkyBankBackend.DTO.CustomerDTO;
import com.sky.SkyBankBackend.services.CustomerServiceDB;

import com.sky.SkyBankBackend.entities.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:test-schema.sql", "classpath:test-data.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class CustomerControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void testCreate() throws Exception {
        CustomerDTO newCustomer = new CustomerDTO(new Customer("Bob", "Smith", "bobsmith@email.com", "strong_password", 123456, 12345678, 1000.00));

        String newCustomerAsJSON = this.mapper.writeValueAsString(newCustomer);

        RequestBuilder req = MockMvcRequestBuilders
                .post("/customer/create")
                .content(newCustomerAsJSON)
                .contentType(MediaType.APPLICATION_JSON);

        ResultMatcher checkStatus = MockMvcResultMatchers.status().isCreated();

        CustomerDTO createdCustomer = new CustomerDTO(new Customer("Bob", "Smith", "bobsmith@email.com", "", 123456, 12345678, 1000.00));
        String createdCustomerAsJSON = this.mapper.writeValueAsString(createdCustomer);

        ResultMatcher checkBody = MockMvcResultMatchers.content().json(createdCustomerAsJSON);

        this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
    }

    @Test
    void testGetCustomerById() throws Exception {
        RequestBuilder req = MockMvcRequestBuilders
                .get("/customer/get/email/john@email.com");
        ResultMatcher checkStatus = MockMvcResultMatchers.status().isOk();

        CustomerDTO toFind = new CustomerDTO(new Customer("John", "Johnson", "john@email.com", "testPassword", 654321, 87654321, 200.00));
        String toFindAsJSON = this.mapper.writeValueAsString(toFind);
        ResultMatcher checkBody = MockMvcResultMatchers.content().json(toFindAsJSON);

        this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
    }

    @Test
    void testGetCustomerByAccountNumber() throws Exception {
        RequestBuilder req = MockMvcRequestBuilders
                .get("/customer/get/accountNumber/87654321");
        ResultMatcher checkStatus = MockMvcResultMatchers.status().isOk();

        CustomerDTO toFind = new CustomerDTO(new Customer("John", "Johnson", "john@email.com", "testPassword", 654321, 87654321, 200.00));
        String toFindAsJSON = this.mapper.writeValueAsString(toFind);
        ResultMatcher checkBody = MockMvcResultMatchers.content().json(toFindAsJSON);

        this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
    }

    @Test
    void testUpdateCustomer() throws Exception {
        CustomerDTO updates = new CustomerDTO(new Customer(null, null, null, null, null, null, 2500.00));

        String updatesAsJSON = this.mapper.writeValueAsString(updates);

        RequestBuilder req = MockMvcRequestBuilders
                .put("/customer/update/john@email.com")
                .content(updatesAsJSON)
                .contentType(MediaType.APPLICATION_JSON);

        ResultMatcher checkStatus = MockMvcResultMatchers.status().isCreated();

        CustomerDTO createdCustomer = new CustomerDTO(new Customer("John", "Johnson", "john@email.com", "testPassword", 654321, 87654321, 2500.00));
        String createdCustomerAsJSON = this.mapper.writeValueAsString(createdCustomer);

        ResultMatcher checkBody = MockMvcResultMatchers.content().json(createdCustomerAsJSON);

        this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
    }
}
