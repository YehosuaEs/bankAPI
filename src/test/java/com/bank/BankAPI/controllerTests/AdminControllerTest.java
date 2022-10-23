package com.bank.BankAPI.controllerTests;

import com.bank.BankAPI.models.AccountHolder;
import com.bank.BankAPI.models.DTO.AccountDTO;
import com.bank.BankAPI.models.accounts.*;
import com.bank.BankAPI.models.others.Address;
import com.bank.BankAPI.models.others.Money;
import com.bank.BankAPI.repositories.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AdminControllerTest {


    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    CheckingRepository checkingRepository;
    @Autowired
    SavingsRepository savingsRepository;
    @Autowired
    CreditCardRepository creditCardRepository;
    @Autowired
    StudentCheckingRepository studentCheckingRepository;
    @Autowired
    AccountHolderRepository accountHolderRepository;

    AccountHolder accountHolder1, accountHolder2;
    Account checkingAcc, savingAcc, creditCardAcc, studentCheckingAcc;

    @BeforeEach
    void setUp() {
        accountHolder1 = accountHolderRepository.save(new AccountHolder(
                "Sabina Some", LocalDate.of(2004, 3, 16),
                new Address("Av. Gracia 66", "Berlin", "12034"),
                new Address("Av. Gran Via 12", "Madrid", "01039")
        ));
        accountHolder2 = accountHolderRepository.save(new AccountHolder(
                "Emilia Son", LocalDate.of(2007, 11, 13),
                new Address("Av. Down 10", "NY", "09234"),
                new Address("Av. Up 23", "Tokio", "99332")
        ));
        checkingAcc = checkingRepository.save(new Checking(
                new Money(new BigDecimal("2300.20")), "abcd", accountHolder1, accountHolder2));
        savingAcc = savingsRepository.save(new Savings(
                new Money(new BigDecimal("1000")), "xxxx", accountHolder1, accountHolder2,
                new Money(new BigDecimal("200")), new Money(new BigDecimal(".4"))
        ));
        creditCardAcc = creditCardRepository.save(new CreditCard(
                new Money(new BigDecimal("1200")), "xxxx", accountHolder1, accountHolder2,
                new Money(new BigDecimal("100")), new Money(new BigDecimal(".1"))
        ));
        studentCheckingAcc = studentCheckingRepository.save(new StudentChecking(
                new Money(new BigDecimal("1000")), "ssss", accountHolder1, accountHolder2
        ));
    }

    @AfterEach
    void tearDown() {
        accountHolderRepository.deleteAll();
        checkingRepository.deleteAll();
        savingsRepository.deleteAll();
        creditCardRepository.deleteAll();
        studentCheckingRepository.deleteAll();
    }

    @Test
    @DisplayName("Creating Checking Accounts by Admins")
    void checkingAccount_create_by_Admin_Ok() throws Exception {
        AccountDTO accountDTO = new AccountDTO("200", "abcd", accountHolder1.getUserId(), accountHolder2.getUserId());

        String body = objectMapper.writeValueAsString(accountDTO);

        MvcResult mvcResult = mockMvc.perform(post("/admin/accounts/add_checking").content(objectMapper.writeValueAsString(body)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains(accountHolder1.getUserId().toString()));

    }


}
