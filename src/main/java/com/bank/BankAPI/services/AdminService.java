package com.bank.BankAPI.services;

import com.bank.BankAPI.models.AccountHolder;
import com.bank.BankAPI.models.DTO.AccountDTO;
import com.bank.BankAPI.models.DTO.ThirdPartyDTO;
import com.bank.BankAPI.models.ThirdParty;
import com.bank.BankAPI.models.accounts.*;
import com.bank.BankAPI.models.others.Money;
import com.bank.BankAPI.repositories.*;
import com.bank.BankAPI.services.interfaces.AdminServiceInterface;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class AdminService implements AdminServiceInterface {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    ThirdPartyRepository thirdPartyRepository;

    // ---- ACCESS BALANCE AND EDIT
    public Account getBalanceFromAccounts(Long id) {
        if (accountRepository.findById(id).isPresent()) {
            return accountRepository.findById(id).get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The Account with the  given ID does not exist");
    }

    public Account updateBalanceFromAccounts(Long id, Money newBalance) {
        Account accountX = accountRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        accountX.setBalance(newBalance);
        return accountRepository.save(accountX);
    }

    // ---- GET ALLL
    public List<Account> getAllAccountsByAdmin() {
        return accountRepository.findAll();
    }


    // ---- CREATE ACCOUNTS
    public Checking addAccountCheckingWithDTO(AccountDTO accountDTO) {
        // LocalDate now = LocalDate.now();
        // LocalDate birthday = accountHolderRepository.findById(); //acceder a la edad guardada del accountHolder en main
        // java.time.Period period = java.time.Period.between(super.getCreationDate(), now);
        // falta el checo de la edad
        BigDecimal balancePre = new BigDecimal(accountDTO.getBalance());
        Money balance = new Money(balancePre);
        AccountHolder primaryOwner = accountHolderRepository.findById(accountDTO.getPrimaryOwnerId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        AccountHolder secondaryOwner = null;
        if (accountDTO.getSecondaryOwnerId() != null) {
            secondaryOwner = accountHolderRepository.findById(accountDTO.getSecondaryOwnerId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        }
        Checking newChecking = new Checking(balance, accountDTO.getSecretKey(), primaryOwner, secondaryOwner);
        return accountRepository.save(newChecking);
    }

    public Savings addAccountSavingsWithDTO(AccountDTO accountDTO) {
        BigDecimal balanceInput = new BigDecimal(accountDTO.getBalance());
        Money balance = new Money(balanceInput);
        AccountHolder primaryOwner = accountHolderRepository.findById(accountDTO.getPrimaryOwnerId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        AccountHolder secondaryOwner = null;
        if (accountDTO.getSecondaryOwnerId() != null) {
            secondaryOwner = accountHolderRepository.findById(accountDTO.getSecondaryOwnerId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        }
        BigDecimal minimBalanceInput = new BigDecimal(accountDTO.getMinimBalance());
        Money minimBalance = new Money(minimBalanceInput);
        BigDecimal interestRateInput = new BigDecimal(accountDTO.getInterestRate());
        Money interestRate = new Money(interestRateInput);
        Savings savingInput = new Savings(balance, accountDTO.getSecretKey(), primaryOwner, secondaryOwner, minimBalance, interestRate);
        return accountRepository.save(savingInput);
    }

    public CreditCard addAccountCreditCardWithDTO(AccountDTO accountDTO) {
        BigDecimal balanceInput = new BigDecimal(accountDTO.getBalance());
        Money balance = new Money(balanceInput);
        AccountHolder primaryOwner = accountHolderRepository.findById(accountDTO.getPrimaryOwnerId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        AccountHolder secondaryOwner = null;
        if (accountDTO.getSecondaryOwnerId() != null) {
            secondaryOwner = accountHolderRepository.findById(accountDTO.getSecondaryOwnerId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        }
        BigDecimal creditLimitInout = new BigDecimal(accountDTO.getCreditLimit());
        Money creditLimit = new Money(creditLimitInout);
        BigDecimal interestRateInput = new BigDecimal(accountDTO.getInterestRate());
        Money interestRate = new Money(interestRateInput);
        CreditCard creditCardInput = new CreditCard(balance, accountDTO.getSecretKey(), primaryOwner, secondaryOwner, creditLimit, interestRate);
        return accountRepository.save(creditCardInput);
    }

    // ---- DELETE ACCOUNTS
    public void deleteAccountById(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public ThirdParty addThirdParty(ThirdPartyDTO thirdPartyDTO) {
        String userName = thirdPartyDTO.getUserName();
        String hashedKey = thirdPartyDTO.getHashedKey();
        ThirdParty newThirdParty = new ThirdParty(userName, hashedKey);
        return thirdPartyRepository.save(newThirdParty);
    }
}
