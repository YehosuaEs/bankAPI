package com.bank.BankAPI.controllers;

import com.bank.BankAPI.models.accounts.Account;
import com.bank.BankAPI.models.accounts.Checking;
import com.bank.BankAPI.models.accounts.CreditCard;
import com.bank.BankAPI.models.accounts.Savings;
import com.bank.BankAPI.models.others.Money;
import com.bank.BankAPI.services.interfaces.AdminServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@RestController
public class AdminController {

    @Autowired
    AdminServiceInterface adminService;

    // ---- GET BY ID
    @GetMapping("/admin/accounts/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Account getBalance(@PathVariable Long id) {
        return adminService.getBalanceFromAccounts(id);
    }

    // ---- GET ALL
    @GetMapping("/admin/accounts/")
    @ResponseStatus(HttpStatus.FOUND)
    public List<Account> getAllAccounts() {
        return adminService.getAllAccountsByAdmin();
    }


    // ---- CREATE ACCOUNTS (Checking, Saving, CreditCard)
    @PostMapping("/admin/accounts/add_checking")
    @ResponseStatus(HttpStatus.CREATED)
    public Checking addCheckingAccount(@RequestBody Checking account) {
        return adminService.addAccountChecking(account);
    }

    @PostMapping("/admin/accounts/add_savings")
    @ResponseStatus(HttpStatus.CREATED)
    public Savings addSavingsAccount(@RequestBody Savings account) {
        return adminService.addAccountSavings(account);
    }

    @PostMapping("/admin/accounts/add_credit-card")
    @ResponseStatus(HttpStatus.CREATED)
    public CreditCard addCheckingAccount(@RequestBody CreditCard account) {
        return adminService.addAccountCreditCard(account);
    }

    // ---- UPDATE BALANCE ACCOUNTS BY ID

    @PatchMapping("/admin/accounts/updateBalance/{id}/{newBalance}")
    @ResponseStatus(HttpStatus.OK)
    public Account updateBalanceFromAccount(@PathVariable Long id, @PathVariable BigDecimal newBalance) {
        return adminService.updateBalanceFromAccounts(id, new Money(newBalance));
    }



    // ---- DELETE ACCOUNTS BY ID
    @DeleteMapping("/admin/accounts/delete/{id}")
    @ResponseStatus(HttpStatus.MOVED_PERMANENTLY)
    public HttpStatus deleteAccountsById(@PathVariable Long id) {
        try {
            adminService.deleteAccountById(id);
            return HttpStatus.OK ;
        } catch (Exception e) {
            System.err.println("the id is not longer exist");
            return HttpStatus.NOT_FOUND;
        }
    }


}