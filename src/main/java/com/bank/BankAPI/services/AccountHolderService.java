package com.bank.BankAPI.services;

import com.bank.BankAPI.models.AccountHolder;
import com.bank.BankAPI.models.DTO.AccountDTO;
import com.bank.BankAPI.models.DTO.AccountHolderDTO;
import com.bank.BankAPI.models.accounts.Account;
import com.bank.BankAPI.models.others.Address;
import com.bank.BankAPI.repositories.*;
import com.bank.BankAPI.services.interfaces.AccountHolderServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

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



    /*
    AccountHolders should be able to access their own account balance
    */
    public Account getBalanceFromCheckingAccountHolder(Long id){
        return checkingRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "THe Checking from AccountHolder with the given ID does not exist"));
    }


   /* public Checking getCheckingAccountByBalance(Long holderId, Money balance) {
        return checkingsRepository.findById(holderId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "A Checking Account with the given id does not exist"));
    }*/
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




    public AccountHolder createAccountHolder(AccountHolderDTO accountDTO) {
        if (LocalDate.now().getYear() - accountDTO.getBirthDate().getYear() < 18) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"The AccountHolder must be 18 years old or older.");
        }
        String name = accountDTO.getUserName();
        LocalDate birthDate = accountDTO.getBirthDate();
        Address mainAddress = new Address(accountDTO.getAddress(), accountDTO.getCity(), accountDTO.getZipCode());
        Address secondaryAddress = null;
        if(accountDTO.getSecondaryAddress() != null){
            secondaryAddress = new Address(accountDTO.getSecondaryAddress(), accountDTO.getSecondaryCity(), accountDTO.getSecondaryZipCode());
        }
        AccountHolder newAccountHolder = new AccountHolder(name, birthDate, mainAddress, secondaryAddress);
        return accountHolderRepository.save(newAccountHolder);
    }
}
