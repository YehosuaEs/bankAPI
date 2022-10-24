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

    @NotNull
    private String userName;
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

    public AccountHolderDTO(String userName, LocalDate birthDate, String address, String city, String zipCode, String secondaryAddress, String secondaryCity, String secondaryZipCode) {
        this.userName = userName;
        this.birthDate = birthDate;
        this.address = address;
        this.city = city;
        this.zipCode = zipCode;
        this.secondaryAddress = secondaryAddress;
        this.secondaryCity = secondaryCity;
        this.secondaryZipCode = secondaryZipCode;
    }


  /* public AccountHolderDTO(String userName, LocalDate birthDate, String address, String city, String zipCode, String secondaryAddress, String secondaryCity, String secondaryZipCode) {
        this.userName = userName;
        this.birthDate = birthDate;
        this.address = address;
        this.city = city;
        this.zipCode = zipCode;
        this.secondaryAddress = secondaryAddress;
        this.secondaryCity = secondaryCity;
        this.secondaryZipCode = secondaryZipCode;
    }*/
}
