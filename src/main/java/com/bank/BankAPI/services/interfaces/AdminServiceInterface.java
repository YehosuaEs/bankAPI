package com.bank.BankAPI.services.interfaces;

import com.bank.BankAPI.models.DTO.AccountDTO;
import com.bank.BankAPI.models.DTO.ThirdPartyDTO;
import com.bank.BankAPI.models.ThirdParty;
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

     Checking addAccountCheckingWithDTO(AccountDTO account);

     Savings addAccountSavingsWithDTO(AccountDTO accountDTO);

     //CreditCard addAccountCreditCard(CreditCard account);
     CreditCard addAccountCreditCardWithDTO(AccountDTO AccountDTO);

     void deleteAccountById(Long id);

     ThirdParty addThirdParty(ThirdPartyDTO thirdPartyDTO);
}
