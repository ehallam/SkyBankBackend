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
        PayeeDTO newPayee = new PayeeDTO(new Payee("Peter", "Hannan", "john@email.com", 653245, 15435471, new Customer()));

        String newPayeeAsJSON = this.mapper.writeValueAsString(newPayee);

        RequestBuilder req = MockMvcRequestBuilders
                .post("/payee/create")
                .content(newPayeeAsJSON)
                .contentType(MediaType.APPLICATION_JSON);

        ResultMatcher checkStatus = MockMvcResultMatchers.status().isCreated();

        PayeeDTO createdPayee = new PayeeDTO(new Payee("Peter", "Hannan", "john@email.com", 653245, 15435471, new Customer()));
        String createdPayeeAsJSON = this.mapper.writeValueAsString(createdPayee);

        ResultMatcher checkBody = MockMvcResultMatchers.content().json(createdPayeeAsJSON);

        this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
    }
}
