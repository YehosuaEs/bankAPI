package com.bank.BankAPI.controllers;

import com.bank.BankAPI.models.AccountHolder;
import com.bank.BankAPI.models.DTO.AccountDTO;
import com.bank.BankAPI.models.DTO.AccountHolderDTO;
import com.bank.BankAPI.models.accounts.Account;
import com.bank.BankAPI.models.accounts.Checking;
import com.bank.BankAPI.services.interfaces.AccountHolderServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AccountHolderController {

    @Autowired
    AccountHolderServiceInterface accountHolderService;

    @GetMapping("/account-holder/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Account getCheckingBalance(Long id){
        return accountHolderService.getBalanceFromCheckingAccountHolder(id);
    }

    @PostMapping("/account_holder/")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountHolder addCheckingAccountHolder(@RequestBody @Valid AccountHolderDTO account){
        return accountHolderService.createAccountHolder(account);
    }
}
