package com.bank.BankAPI.models.DTO;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AccountDTO {

    @NotNull
    private String balance;
    private String userName;
    private String secretKey;
    @NotNull
    private Long primaryOwnerId;
    private Long secondaryOwnerId;
    private String minimBalance;
    private String interestRate;
    private String creditLimit;
    private LocalDate birthDate;
    private String address;
    private String city;
    private String zipCode;
    private String secondaryAddress;
    private String secondaryCity;
    private String secondaryZipCode;

    public AccountDTO(String balance, Long primaryOwnerId, Long secondaryOwnerId, String secretKey, String minimBalance, String interestRates) {
        this.balance = balance;
        this.primaryOwnerId = primaryOwnerId;
        this.secondaryOwnerId = secondaryOwnerId;
        this.secretKey = secretKey;
        this.minimBalance = minimBalance;
        this.interestRate = interestRates;
    }

    public AccountDTO(String balance, String secretKey, Long primaryOwnerId, Long secondaryOwnerId) {
        this.balance = balance;
        this.secretKey = secretKey;
        this.primaryOwnerId = primaryOwnerId;
        this.secondaryOwnerId = secondaryOwnerId;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public Long getPrimaryOwnerId() {
        return primaryOwnerId;
    }

    public void setPrimaryOwnerId(Long primaryOwnerId) {
        this.primaryOwnerId = primaryOwnerId;
    }

    public Long getSecondaryOwnerId() {
        return secondaryOwnerId;
    }

    public void setSecondaryOwnerId(Long secondaryOwnerId) {
        this.secondaryOwnerId = secondaryOwnerId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getMinimBalance() {
        return minimBalance;
    }

    public void setMinimBalance(String minimBalance) {
        this.minimBalance = minimBalance;
    }

    public String getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(String interestRate) {
        this.interestRate = interestRate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(String creditLimit) {
        this.creditLimit = creditLimit;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getSecondaryAddress() {
        return secondaryAddress;
    }

    public void setSecondaryAddress(String secondaryAddress) {
        this.secondaryAddress = secondaryAddress;
    }

    public String getSecondaryCity() {
        return secondaryCity;
    }

    public void setSecondaryCity(String secondaryCity) {
        this.secondaryCity = secondaryCity;
    }

    public String getSecondaryZipCode() {
        return secondaryZipCode;
    }

    public void setSecondaryZipCode(String secondaryZipCode) {
        this.secondaryZipCode = secondaryZipCode;
    }
}
