package com.bank.BankAPI.services.interfaces;

import com.bank.BankAPI.models.accounts.Account;
import com.bank.BankAPI.models.accounts.Checking;
import com.bank.BankAPI.models.accounts.CreditCard;
import com.bank.BankAPI.models.accounts.Savings;
import com.bank.BankAPI.models.others.Money;

import java.util.List;
import java.util.Set;

public interface AdminServiceInterface {

     Account getBalanceFromAccounts(Long id);
     Account updateBalanceFromAccounts(Long id, Money newBalance);
     List<Account> getAllAccountsByAdmin();
     Checking addAccountChecking(Checking account);

     Savings addAccountSavings(Savings account);

     CreditCard addAccountCreditCard(CreditCard account);

     void deleteAccountById(Long id);
}
