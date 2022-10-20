package com.bank.BankAPI.services.interfaces;

import com.bank.BankAPI.models.AccountHolder;
import com.bank.BankAPI.models.DTO.AccountDTO;
import com.bank.BankAPI.models.DTO.AccountHolderDTO;
import com.bank.BankAPI.models.DTO.TransferAccountDTO;
import com.bank.BankAPI.models.accounts.Account;
import com.bank.BankAPI.models.accounts.Checking;
import com.bank.BankAPI.models.others.Money;

import java.util.List;
import java.util.Set;

public interface AccountHolderServiceInterface {


    Money getBalanceFromAccountHolder(Long id);

    Money makeTransferWithAccountDTO(TransferAccountDTO transferAccountDTO);





}
