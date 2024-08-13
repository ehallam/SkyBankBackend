package com.sky.SkyBankBackend.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sky.SkyBankBackend.DTO.TransactionDTO;
import com.sky.SkyBankBackend.entities.Customer;
import com.sky.SkyBankBackend.entities.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TransactionControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void testCreate() throws Exception {
        Customer testCustomer = new Customer("John", "Johnson", "john@email.com", "testPassword", 654321, 87654321, 200.00);
        TransactionDTO newTransaction = new TransactionDTO(new Transaction("Test payment", LocalDate.of(2024, 8, 8), 156.78, 0.00, new Customer("John", "Johnson", "john@email.com", "testPassword", 654321, 87654321, 200.00), 87654321, 654321));
        String newCustomerAsJSON = this.mapper.writeValueAsString(newTransaction);

        RequestBuilder req = MockMvcRequestBuilders
                .post("/transaction/create")
                .content(newCustomerAsJSON)
                .contentType(MediaType.APPLICATION_JSON);

        ResultMatcher checkStatus = MockMvcResultMatchers.status().isCreated();

        TransactionDTO createdTransaction = new TransactionDTO(new Transaction("Test payment", LocalDate.of(2024, 8, 8), 156.78, 0.00, testCustomer, 87654321, 654321));
        Transaction t = new Transaction("Test payment", LocalDate.of(2024, 8, 8), 156.78, 0.00, testCustomer, 87654321, 654321);
        System.out.println(t.getId());
        String createdTransactionAsJSON = this.mapper.writeValueAsString(createdTransaction);

        ResultMatcher checkBody = MockMvcResultMatchers.content().json(createdTransactionAsJSON);

        this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
    }
}
