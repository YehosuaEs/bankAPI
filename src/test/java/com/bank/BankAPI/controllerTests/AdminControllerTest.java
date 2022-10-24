package com.bank.BankAPI.controllerTests;

import com.bank.BankAPI.models.AccountHolder;
import com.bank.BankAPI.models.DTO.AccountDTO;
import com.bank.BankAPI.models.DTO.AccountHolderDTO;
import com.bank.BankAPI.models.DTO.ThirdPartyDTO;
import com.bank.BankAPI.models.ThirdParty;
import com.bank.BankAPI.models.accounts.*;
import com.bank.BankAPI.models.others.Address;
import com.bank.BankAPI.models.others.Money;
import com.bank.BankAPI.repositories.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AdminControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @Autowired
    AccountRepository accountRepository;
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

    @Autowired
    ThirdPartyRepository thirdPartyRepository;

    AccountHolder accountHolder1, accountHolder2;
    Account checkingAcc, savingAcc, creditCardAcc, studentCheckingAcc;

    ThirdParty thirdParty1;


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        accountHolder1 = accountHolderRepository.save(new AccountHolder(
                "Sabina Some", LocalDate.of(2004, 3, 16),
                new Address("Av. Gracia 66", "Berlin", "12034"),
                new Address("Av. Gran Via 12", "Madrid", "01039")
        ));
        accountHolder2 = accountHolderRepository.save(new AccountHolder(
                "Emilia Son", LocalDate.of(2000, 11, 13),
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
        thirdParty1 = thirdPartyRepository.save(new ThirdParty("Regina", "axxd"));
    }

    @AfterEach
    void tearDown() {
        //  checkingRepository.deleteAll();
        //  savingsRepository.deleteAll();
        //  creditCardRepository.deleteAll();
        //  studentCheckingRepository.deleteAll();
        //  accountHolderRepository.deleteAll();
    }


    @Test
    @DisplayName("Get Balance by ID Account test")
    void get_Balance_by_ID_Account_Ok() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/admin/accounts/1")).andExpect(status().isFound()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains(accountRepository.findById(1l).get().getBalance().getAmount().toString()));
    }

    @Test
    @DisplayName("Get All Accounts test")
    void get_All_Accounts_Ok() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/admin/accounts/")).andExpect(status().isFound()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Sabina Some"));
    }

    @Test
    @DisplayName("Creating Checking Accounts by Admin test")
    void checkingAccount_create_by_Admin_Ok() throws Exception {
        AccountDTO accountDTO = new AccountDTO("200", "abcd", accountHolder1.getUserId(), accountHolder2.getUserId());

        String body = objectMapper.writeValueAsString(accountDTO);

        MvcResult mvcResult = mockMvc.perform(post("/admin/accounts/add_checking").content(body)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains(accountHolder1.getUserId().toString()));

    }

    @Test
    @DisplayName("Creating Savings Accounts by Admin test")
    void savingAccount_create_by_Admin_Ok() throws Exception {
        AccountDTO accountDTO = new AccountDTO("200", "abcd", accountHolder1.getUserId(), accountHolder2.getUserId(), "200", ".4");

        String body = objectMapper.writeValueAsString(accountDTO);

        MvcResult mvcResult = mockMvc.perform(post("/admin/accounts/add_savings").content(body)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains(accountHolder1.getUserId().toString()));

    }

    @Test
    @DisplayName("Creating CreditCard Accounts by Admin test")
    void creditCard_Create_by_Admin_Ok() throws Exception {
        AccountDTO accountDTO = new AccountDTO("2000", accountHolder1.getUserId(), accountHolder2.getUserId(), "axax", "120", ".2");

        String body = objectMapper.writeValueAsString(accountDTO);

        MvcResult mvcResult = mockMvc.perform(post("/admin/accounts/add_credit-card").content(body)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains(accountHolder1.getUserId().toString()));
    }

    @Test
    @DisplayName("Update Balance test")
    void Update_Balance_ok() throws Exception {
        Long id = 1L;
        String balance = "200";
        MvcResult mvcResult = mockMvc.perform(patch("/admin/accounts/updateBalance").param("id", String.valueOf(id)).param("balance", balance))
                .andExpect(status().isOk()).andReturn();

        assertTrue(accountRepository.findById(1L).get().getBalance().getAmount().compareTo(new BigDecimal("200")) == 0);
    }

    @Test
    @DisplayName("Delete Account test")
    void delete_Account_Ok() throws Exception {
        MvcResult mvcResult = mockMvc.perform(delete("/admin/accounts/delete/1")).andExpect(status().isMovedPermanently()).andReturn();
        assertTrue(accountRepository.findById(1L).isEmpty());
    }


    @Test
    @DisplayName("Creating a Third party test")
    void create_ThirdParty_Ok() throws Exception {
        ThirdPartyDTO thirdPartyDTO = new ThirdPartyDTO("Emilia", "xabc");
        String body = objectMapper.writeValueAsString(thirdPartyDTO);
        MvcResult mvcResult = mockMvc.perform(post("/admin/third_party/").content(body)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains(thirdPartyDTO.getHashedKey()));
    }

    @Test
    @DisplayName("Getting all ThirdParties")
    void getting_All_ThirdParties_Ok() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/admin/third_party/")).andExpect(status().isFound()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Regina"));
    }


    @Test
    @DisplayName("Creating Account Holders")
    void creatin_AccountHolders_ok() throws Exception {
        AccountHolderDTO accountHolderDTO = new AccountHolderDTO("Dario Son", LocalDate.of(1992, 2, 24), "Calabria 21", "BCN", "08010", null, null, null);
        String body = objectMapper.writeValueAsString(accountHolderDTO);

        MvcResult mvcResult = mockMvc.perform(post("/admin/account_holder/").content(body)
               .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains(accountHolderDTO.getUserName()));
    }
}
