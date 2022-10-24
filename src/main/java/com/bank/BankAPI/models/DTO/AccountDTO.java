package com.bank.BankAPI.models.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class AccountDTO {

    @NotNull
    private String balance;
    private String secretKey;
    @NotNull
    private Long primaryOwnerId;
    private Long secondaryOwnerId;
    private String minimBalance;
    private String interestRate;
    private String creditLimit;




    public AccountDTO(String balance, String secretKey, Long primaryOwnerId, Long secondaryOwnerId) {
        this.balance = balance;
        this.secretKey = secretKey;
        this.primaryOwnerId = primaryOwnerId;
        this.secondaryOwnerId = secondaryOwnerId;
    }


    public AccountDTO(String balance, String secretKey, Long primaryOwnerId, Long secondaryOwnerId, String minimBalance, String interestRate) {
        this.balance = balance;
        this.secretKey = secretKey;
        this.primaryOwnerId = primaryOwnerId;
        this.secondaryOwnerId = secondaryOwnerId;
        this.minimBalance = minimBalance;
        this.interestRate = interestRate;
    }

    public AccountDTO(String balance,  Long primaryOwnerId, Long secondaryOwnerId, String secretKey, String creditLimit, String interestRate){
        this.balance = balance;
        this.secretKey = secretKey;
        this.primaryOwnerId = primaryOwnerId;
        this.secondaryOwnerId = secondaryOwnerId;
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
    }


}
