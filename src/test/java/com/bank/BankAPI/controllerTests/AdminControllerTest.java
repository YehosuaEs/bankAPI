package com.bank.BankAPI.controllerTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
public class AdminControllerTest {


    @Autowired
    private WebApplicationContext webApplicationContext;


    private final ObjectMapper objectMapper = new ObjectMapper();

}
