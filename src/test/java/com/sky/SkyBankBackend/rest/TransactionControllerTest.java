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
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

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
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("user"));

        ResultMatcher checkStatus = MockMvcResultMatchers.status().isCreated();
        LocalDate date = LocalDate.of(2024, 8, 8);
        Transaction t = new Transaction("Test payment", date, 156.78, 0.00, testCustomer, 87654321, 654321);
        TransactionDTO createdTransaction = new TransactionDTO(t);
        createdTransaction.setId(4);
        String createdTransactionAsJSON = this.mapper.writeValueAsString(createdTransaction);

        ResultMatcher checkBody = MockMvcResultMatchers.content().json(createdTransactionAsJSON);

        this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
    }

    @Test
    void testGetAll() throws Exception {

        RequestBuilder req = MockMvcRequestBuilders.get("/transaction/getAll/john@email.com/87654321").with(user("user"));
        ResultMatcher checkStatus = MockMvcResultMatchers.status().isOk();

        Customer john = new Customer("John", "Johnson", "john@email.com", "testPassword", 654321, 87654321, 200.00);
        Customer bob = new Customer("Bob", "Smith", "bob@email.com", "secure", 654322, 87654322, 200.00);

        Transaction firstTransaction = new Transaction("Test transaction 1", LocalDate.of(2024, 8, 15), null, 15.52, john, 13245768, 132456);
        Transaction secondTransaction = new Transaction("Test transaction 2", LocalDate.of(2024, 8, 15), 56.00, null, bob, 87654321, 654321);

        firstTransaction.setId(1);
        secondTransaction.setId(2);

        List<TransactionDTO> transactions = List.of(
                new TransactionDTO(firstTransaction),
                new TransactionDTO(secondTransaction)
        );

        String transactionsAsJSON = this.mapper.writeValueAsString(transactions);
        ResultMatcher checkBody = MockMvcResultMatchers.content().json(transactionsAsJSON);

        this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
    }
}