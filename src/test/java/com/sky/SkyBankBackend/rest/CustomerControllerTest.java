package com.sky.SkyBankBackend.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sky.SkyBankBackend.DTO.CustomerDTO;
import com.sky.SkyBankBackend.entities.Customer;
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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CustomerControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void testCreate() throws Exception {
        CustomerDTO newCustomer = new CustomerDTO(new Customer("Bob", "Smith", "bobsmith@email.com", "strong_password", 123456, 12345678, 1000));

        String newCustomerAsJSON = this.mapper.writeValueAsString(newCustomer);

        RequestBuilder req = MockMvcRequestBuilders
                .post("/customer/create")
                .content(newCustomerAsJSON)
                .contentType(MediaType.APPLICATION_JSON);

        ResultMatcher checkStatus = MockMvcResultMatchers.status().isCreated();

        CustomerDTO createdCustomer = new CustomerDTO(new Customer("Bob", "Smith", "bobsmith@email.com", "strong_password", 123456, 12345678, 1000));
        String createdCustomerAsJSON = this.mapper.writeValueAsString(createdCustomer);

        ResultMatcher checkBody = MockMvcResultMatchers.content().json(createdCustomerAsJSON);

        this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
    }

    @Test
    void testGetCustomerById() throws Exception {
        RequestBuilder req = MockMvcRequestBuilders
                .get("/car/get/2");
        ResultMatcher checkStatus = MockMvcResultMatchers.status().isOk();

        CustomerDTO toFind = new CustomerDTO(new Customer("Bob", "Smith", "bobsmith@email.com", "strong_password", 123456, 12345678, 1000));
        String toFindAsJSON = this.mapper.writeValueAsString(toFind);
        ResultMatcher checkBody = MockMvcResultMatchers.content().json(toFindAsJSON);

        this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
    }
}
