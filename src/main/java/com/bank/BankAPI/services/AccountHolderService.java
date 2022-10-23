package com.bank.BankAPI.services;

import com.bank.BankAPI.models.AccountHolder;
import com.bank.BankAPI.models.DTO.AccountDTO;
import com.bank.BankAPI.models.DTO.AccountHolderDTO;
import com.bank.BankAPI.models.DTO.TransferAccountDTO;
import com.bank.BankAPI.models.accounts.Account;
import com.bank.BankAPI.models.others.Address;
import com.bank.BankAPI.models.others.Money;
import com.bank.BankAPI.repositories.*;
import com.bank.BankAPI.services.interfaces.AccountHolderServiceInterface;
import org.springdoc.core.converters.models.MonetaryAmount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
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
        if (accountRepository.findById(id).isPresent()) {
            return accountRepository.findById(id).get().getBalance();
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The  AccountHolder with the given ID does not exist.");
    }

    /* Account holders should be able to transfer money from any of their accounts to any other account (regardless of owner).
    The transfer should only be processed if the account has sufficient funds.
    The user must provide the Primary or Secondary owner name and the id of the account that should receive the transfer. */
    public Money makeTransferWithAccountDTO(TransferAccountDTO transferAccountDTO) {
        Account accountSender = accountRepository.findById(transferAccountDTO.getSenderAccountId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Account accountReceiving = accountRepository.findById(transferAccountDTO.getReceivingAccountId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (accountReceiving.getPrimaryOwner().getUserName().equals(transferAccountDTO.getReceivingFullName()) || accountReceiving.getSecondaryOwner().getUserName().equals(transferAccountDTO.getReceivingFullName())) {
            // Checking if the sender has the proper amount to continue the transaction
            BigDecimal amountFrom = new BigDecimal(transferAccountDTO.getTransferAmount());
            BigDecimal balanceFrom = new BigDecimal(accountSender.getBalance().getAmount().doubleValue());
            BigDecimal difference = balanceFrom.subtract(amountFrom);
            if (difference.doubleValue() < 0) {
                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "The sender account has not enough balance to complete the transaction");
            }
            Money newBalanceFrom = new Money(difference);
            accountSender.setBalance(newBalanceFrom);
            accountRepository.save(accountSender);
            // Giving to the receiving account the amount from the sender
            BigDecimal balanceTo = new BigDecimal(accountReceiving.getBalance().getAmount().doubleValue());
            BigDecimal balancePlusAmountFrom = balanceTo.add(amountFrom);
            Money newBalanceTo = new Money(balancePlusAmountFrom);
            accountReceiving.setBalance(newBalanceTo);
            accountRepository.save(accountReceiving);
            return accountSender.getBalance();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The name is not attached to the provide account");
        }
    }

}
