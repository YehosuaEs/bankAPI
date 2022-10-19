package com.bank.BankAPI.services;

import com.bank.BankAPI.models.AccountHolder;
import com.bank.BankAPI.models.accounts.*;
import com.bank.BankAPI.models.others.Money;
import com.bank.BankAPI.repositories.*;
import com.bank.BankAPI.services.interfaces.AdminServiceInterface;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@Service
public class AdminService implements AdminServiceInterface {

    @Autowired
    AccountRepository accountRepository;
    // ---- ACCESS BALANCE AND EDIT
    public Account getBalanceFromAccounts(Long id){
        if(accountRepository.findById(id).isPresent()){
            return accountRepository.findById(id).get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The Account with the  given ID does not exist");
    }

    public Account updateBalanceFromAccounts(Long id, Money newBalance){
        Account accountX = accountRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        accountX.setBalance(newBalance);
        return  accountRepository.save(accountX);
    }


    // ---- GET ALLL
    public List<Account> getAllAccountsByAdmin(){
        return accountRepository.findAll();
    }

    // ---- CREATE ACCOUNTS
    public Checking addAccountChecking(Checking account) {
        return accountRepository.save(account);
    }

    public Savings addAccountSavings(Savings account) {
        return accountRepository.save(account);
    }

    public CreditCard addAccountCreditCard(CreditCard account) {
        return accountRepository.save(account);
    }

    // ---- DELETE ACCOUNTS

    public void deleteAccountById(Long id) {
        accountRepository.deleteById(id);
    }
}
