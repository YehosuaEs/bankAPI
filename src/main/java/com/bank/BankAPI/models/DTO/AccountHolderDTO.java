package com.bank.BankAPI.models.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class AccountHolderDTO {

    private String balance;
    @NotNull
    private String userName;
    private String secretKey;
    private Long primaryOwnerId;
    private Long secondaryOwnerId;
    private String minimBalance;
    private String interestRate;
    private String creditLimit;
    @NotNull
    private LocalDate birthDate;
    @NotNull
    private String address;
    @NotNull
    private String city;
    @NotNull
    private String zipCode;
    private String secondaryAddress;
    private String secondaryCity;
    private String secondaryZipCode;


    public AccountHolderDTO(String balance, String userName, String secretKey, Long primaryOwnerId, Long secondaryOwnerId, String minimBalance, String interestRate, String creditLimit, LocalDate birthDate, String address, String city, String zipCode, String secondaryAddress, String secondaryCity, String secondaryZipCode) {
        this.balance = balance;
        this.userName = userName;
        this.secretKey = secretKey;
        this.primaryOwnerId = primaryOwnerId;
        this.secondaryOwnerId = secondaryOwnerId;
        this.minimBalance = minimBalance;
        this.interestRate = interestRate;
        this.creditLimit = creditLimit;
        this.birthDate = birthDate;
        this.address = address;
        this.city = city;
        this.zipCode = zipCode;
        this.secondaryAddress = secondaryAddress;
        this.secondaryCity = secondaryCity;
        this.secondaryZipCode = secondaryZipCode;
    }


}
