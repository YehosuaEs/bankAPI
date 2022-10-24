package com.bank.BankAPI.services;

import com.bank.BankAPI.models.AccountHolder;
import com.bank.BankAPI.models.DTO.AccountDTO;
import com.bank.BankAPI.models.DTO.AccountHolderDTO;
import com.bank.BankAPI.models.DTO.ThirdPartyDTO;
import com.bank.BankAPI.models.ThirdParty;
import com.bank.BankAPI.models.accounts.*;
import com.bank.BankAPI.models.others.Address;
import com.bank.BankAPI.models.others.Money;
import com.bank.BankAPI.repositories.*;
import com.bank.BankAPI.services.interfaces.AdminServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class AdminService implements AdminServiceInterface {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CheckingRepository checkingRepository;
    @Autowired
    StudentCheckingRepository studentCheckingRepository;
    @Autowired
    SavingsRepository savingsRepository;
    @Autowired
    CreditCardRepository creditCardRepository;

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

    public Account updateBalanceFromAccounts(Long id, Money balance) {
        Account accountX = accountRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        accountX.setBalance(balance);
        return accountRepository.save(accountX);
    }

    // ---- GET ALLL
    public List<Account> getAllAccountsByAdmin() {
        return accountRepository.findAll();
    }


    // ---- CREATE ACCOUNTS
    public Account addAccountCheckingWithDTO(AccountDTO accountDTO) {
        BigDecimal balancePre = new BigDecimal(accountDTO.getBalance());
        Money balance = new Money(balancePre);
        AccountHolder primaryOwner = accountHolderRepository.findById(accountDTO.getPrimaryOwnerId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        AccountHolder secondaryOwner = null;
        if (accountDTO.getSecondaryOwnerId() != null) {
            secondaryOwner = accountHolderRepository.findById(accountDTO.getSecondaryOwnerId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        }
        if(Period.between(primaryOwner.getBirthDate(), LocalDate.now()).getYears() < 24){
            StudentChecking studentChecking = new StudentChecking(balance, accountDTO.getSecretKey(), primaryOwner, secondaryOwner);
            studentCheckingRepository.save(studentChecking);
            return  accountRepository.save(studentChecking);
        }
        Checking newChecking = new Checking(balance, accountDTO.getSecretKey(), primaryOwner, secondaryOwner);
        checkingRepository.save(newChecking);
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
        savingsRepository.save(savingInput);
        return accountRepository.save(savingInput); //debería guardarlo en el Savings? y lodemas igual?
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
        creditCardRepository.save(creditCardInput);
        return accountRepository.save(creditCardInput);
    }

    // ---- CRREATE THIRDPARTY
    @Override
    public ThirdParty addThirdParty(ThirdPartyDTO thirdPartyDTO) {
        String userName = thirdPartyDTO.getUserName();
        String hashedKey = thirdPartyDTO.getHashedKey();
        ThirdParty newThirdParty = new ThirdParty(userName, hashedKey);
        return thirdPartyRepository.save(newThirdParty);
    }
    public List<ThirdParty> getAllThirdPartiesByAdmin() {
        return thirdPartyRepository.findAll();
    }

    // ---- DELETE ACCOUNTS
    public void deleteAccountById(Long id) {
        accountRepository.deleteById(id);
    }


    // ---- CRREATE ACCOUNTHOLDER
    public AccountHolder createAccountHolderWITHDTO(AccountHolderDTO accountDTO){
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
