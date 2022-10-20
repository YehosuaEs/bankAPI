package com.bank.BankAPI.controllers;

import com.bank.BankAPI.models.AccountHolder;
import com.bank.BankAPI.models.DTO.AccountDTO;
import com.bank.BankAPI.models.DTO.AccountHolderDTO;
import com.bank.BankAPI.models.DTO.TransferAccountDTO;
import com.bank.BankAPI.models.accounts.Account;
import com.bank.BankAPI.models.accounts.Checking;
import com.bank.BankAPI.models.others.Money;
import com.bank.BankAPI.repositories.AccountRepository;
import com.bank.BankAPI.services.interfaces.AccountHolderServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
public class AccountHolderController {

    @Autowired
    AccountHolderServiceInterface accountHolderService;

    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/account_holder/balance/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Money getCheckingBalance(@PathVariable Long id) {
        return accountHolderService.getBalanceFromAccountHolder(id);
    }

    @PatchMapping("/account_holder/transfer/")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Money transferFromAccountHolderToOther(@RequestBody TransferAccountDTO transferAccountDTO){
        return accountHolderService.makeTransferWithAccountDTO(transferAccountDTO);
    }




}
