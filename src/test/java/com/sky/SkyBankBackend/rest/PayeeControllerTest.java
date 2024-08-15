package com.sky.SkyBankBackend.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sky.SkyBankBackend.DTO.CustomerDTO;
import com.sky.SkyBankBackend.DTO.PayeeDTO;
import com.sky.SkyBankBackend.entities.Customer;
import com.sky.SkyBankBackend.entities.Payee;
import com.sky.SkyBankBackend.services.CustomerServiceDB;
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

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:test-schema.sql", "classpath:test-data.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class PayeeControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void testCreate() throws Exception {
        Customer testCustomer = new Customer("John", "Johnson", "john@email.com", "testPassword", 654321, 87654321, 200.00);
        PayeeDTO newPayee = new PayeeDTO(new Payee("Peter", "Hannan", "john@email.com", 653245, 15435471, testCustomer));

        String newPayeeAsJSON = this.mapper.writeValueAsString(newPayee);

        RequestBuilder req = MockMvcRequestBuilders
                .post("/payee/create")
                .content(newPayeeAsJSON)
                .contentType(MediaType.APPLICATION_JSON);

        ResultMatcher checkStatus = MockMvcResultMatchers.status().isCreated();

        PayeeDTO createdPayee = new PayeeDTO(new Payee("Peter", "Hannan", "john@email.com", 653245, 15435471, testCustomer));
        createdPayee.setId(4);
        String createdPayeeAsJSON = this.mapper.writeValueAsString(createdPayee);

        ResultMatcher checkBody = MockMvcResultMatchers.content().json(createdPayeeAsJSON);

        this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
    }

    @Test
    void testDelete() throws Exception {
        Customer testCustomer = new Customer("John", "Johnson", "john@email.com", "testPassword", 654321, 87654321, 200.00);

        RequestBuilder req = MockMvcRequestBuilders.delete("/payee/remove/13245768/john@email.com");
        ResultMatcher checkStatus = MockMvcResultMatchers.status().isOk();
        PayeeDTO toFind = new PayeeDTO(new Payee("Sarah", "Smith", "john@email.com", 132456, 13245768, testCustomer));
        toFind.setId(1);
        String toFindAsJSON = this.mapper.writeValueAsString(toFind);
        ResultMatcher checkBody = MockMvcResultMatchers.content().json(toFindAsJSON);

        this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
    }

    @Test
    void testGetAll() throws Exception {
        RequestBuilder req = MockMvcRequestBuilders.get("/payee/getAll/john@email.com");
        ResultMatcher checkStatus = MockMvcResultMatchers.status().isOk();
        Customer testCustomer = new Customer("John", "Johnson", "john@email.com", "testPassword", 654321, 87654321, 200.00);
        Payee firstPayee = new Payee("Sarah", "Smith", "john@email.com", 132456, 13245768, testCustomer);
        Payee secondPayee = new Payee("Charlie", "Mann", "john@email.com", 555555, 88888888, testCustomer);

        firstPayee.setId(1);
        secondPayee.setId(2);

        List<PayeeDTO> payees = List.of(
                new PayeeDTO(firstPayee),
                new PayeeDTO(secondPayee)
        );

        String payeesAsJSON = this.mapper.writeValueAsString(payees);
        ResultMatcher checkBody = MockMvcResultMatchers.content().json(payeesAsJSON);

        this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);

    }
}