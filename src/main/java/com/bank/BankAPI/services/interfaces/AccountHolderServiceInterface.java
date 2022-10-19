package com.bank.BankAPI.services.interfaces;

import com.bank.BankAPI.models.AccountHolder;
import com.bank.BankAPI.models.DTO.AccountDTO;
import com.bank.BankAPI.models.DTO.AccountHolderDTO;
import com.bank.BankAPI.models.accounts.Account;
import com.bank.BankAPI.models.accounts.Checking;

public interface AccountHolderServiceInterface {

    Account getBalanceFromCheckingAccountHolder(Long id);


    AccountHolder createAccountHolder(AccountHolderDTO accountDTO);
}
