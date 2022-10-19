package com.bank.BankAPI.services;

import com.bank.BankAPI.models.AccountHolder;
import com.bank.BankAPI.models.accounts.Account;
import com.bank.BankAPI.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AccountHolderService {

    @Autowired
    AccountRepository accountRepository;


    /*
    AccountHolders should be able to access their own account balance
    */
    /*public Account getBalanceFromAccounts(Long id){
        if(accountRepository.findById(id).isPresent()){
            return accountRepository.findById(id).get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The Account with the  given ID does not exist");
    }*/




   /*
   Account holders should be able to transfer money from any of their
   accounts to any other account (regardless of owner).
   The transfer should only be processed if the account has sufficient funds.
   The user must provide the Primary or Secondary owner name and the id of the account that should receive the transfer.
    */
}
