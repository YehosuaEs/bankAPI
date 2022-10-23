package com.bank.BankAPI.services;

import com.bank.BankAPI.models.DTO.ThirdPartyDTO;
import com.bank.BankAPI.models.ThirdParty;
import com.bank.BankAPI.models.accounts.Account;
import com.bank.BankAPI.models.others.Money;
import com.bank.BankAPI.repositories.AccountRepository;
import com.bank.BankAPI.repositories.ThirdPartyRepository;
import com.bank.BankAPI.services.interfaces.ThirdPartyServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Service
public class ThirdPartyService implements ThirdPartyServiceInterface {

    @Autowired
    ThirdPartyRepository thirdPartyRepository;

    @Autowired
    AccountRepository accountRepository;

    /*
    There must be a way for third-party users to receive and send money to other accounts.
    Third-Party Users must provide their hashed key in the header of the HTTP request.
    They also must provide the amount, the Account id and the account secret key.
    */

    public void makeThirdPartyTransferToAccounts(String hashedKey, ThirdPartyDTO thirdPartyDTO){
        ThirdParty senderThirdParty = thirdPartyRepository.findById(thirdPartyDTO.getSenderThirdPartyId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The ThirdParty sender is not exist"));
        Account accountReceiving = accountRepository.findById(thirdPartyDTO.getReceivingAccountId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The account you want to send the money is not exist"));
        String secretKey = accountReceiving.getSecretKey();
        String secretKeyDTO = thirdPartyDTO.getSecretKey();
        if (!hashedKey.equals(senderThirdParty.getHashedKey())){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The Hashed key is not correct ");
        }
        if(!secretKey.equals(secretKeyDTO)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The Secret key is not correct ");
        }
        BigDecimal amountFrom = new BigDecimal(thirdPartyDTO.getTransferAmount());
        BigDecimal balanceTo = new BigDecimal(accountReceiving.getBalance().getAmount().doubleValue());
        BigDecimal balancePlusAmountFrom = balanceTo.add(amountFrom);
        Money newBalanceTo = new Money(balancePlusAmountFrom);
        accountReceiving.setBalance(newBalanceTo);
        accountRepository.save(accountReceiving);
    }



}
