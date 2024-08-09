package com.sky.SkyBankBackend.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sky.SkyBankBackend.entities.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void testCreate() throws Exception {
        Transaction toCreate = new Transaction(null, )
    }
}
