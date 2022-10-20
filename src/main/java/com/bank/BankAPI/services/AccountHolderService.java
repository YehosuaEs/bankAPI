package com.bank.BankAPI.services;

import com.bank.BankAPI.models.AccountHolder;
import com.bank.BankAPI.models.DTO.AccountDTO;
import com.bank.BankAPI.models.DTO.AccountHolderDTO;
import com.bank.BankAPI.models.accounts.Account;
import com.bank.BankAPI.models.others.Address;
import com.bank.BankAPI.models.others.Money;
import com.bank.BankAPI.repositories.*;
import com.bank.BankAPI.services.interfaces.AccountHolderServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class AccountHolderService implements AccountHolderServiceInterface {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    CheckingRepository checkingRepository;
    @Autowired
    SavingsRepository savingsRepository;
    @Autowired
    CreditCardRepository creditCardRepository;
    @Autowired
    StudentCheckingRepository studentCheckingRepository;


    /*AccountHolders should be able to access their own account balance*/
    public Money getBalanceFromAccountHolder(Long id) {
        if(accountRepository.findById(id).isPresent()){
            return accountRepository.findById(id).get().getBalance();
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The  AccountHolder with the given ID does not exist.");
    }
   /*
   Account holders should be able to transfer money from any of their
   accounts to any other account (regardless of owner).
   The transfer should only be processed if the account has sufficient funds.
   The user must provide the Primary or Secondary owner name and the id of the account that should receive the transfer.
    */




}
