package com.sky.SkyBankBackend.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sky.SkyBankBackend.DTO.TransactionDTO;
import com.sky.SkyBankBackend.config.SecurityConfig;
import com.sky.SkyBankBackend.entities.Customer;
import com.sky.SkyBankBackend.entities.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:test-schema.sql", "classpath:test-data.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class TransactionControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void testCreate() throws Exception {
        Customer testCustomer = new Customer("John", "Johnson", "john@email.com", "testPassword", 654321, 87654321, 200.00);
        TransactionDTO newTransaction = new TransactionDTO(new Transaction("Test payment", LocalDate.of(2024, 8, 8), 156.78, 0.00, testCustomer, 87654321, 654321));
        String newCustomerAsJSON = this.mapper.writeValueAsString(newTransaction);

        RequestBuilder req = MockMvcRequestBuilders
                .post("/transaction/create")
                .content(newCustomerAsJSON)
                .contentType(MediaType.APPLICATION_JSON);

        ResultMatcher checkStatus = MockMvcResultMatchers.status().isCreated();
        Transaction t = new Transaction("Test payment", LocalDate.of(2024, 8, 8), 156.78, 0.00, testCustomer, 87654321, 654321);
        TransactionDTO createdTransaction = new TransactionDTO(t);
        createdTransaction.setId(1);
        String createdTransactionAsJSON = this.mapper.writeValueAsString(createdTransaction);

        ResultMatcher checkBody = MockMvcResultMatchers.content().json(createdTransactionAsJSON);

        this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
    }

    @Test
    void testGetAll() throws Exception {

    }
}