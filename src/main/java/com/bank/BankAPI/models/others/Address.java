package com.bank.BankAPI.models.others;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class Address {
    private String fullAddress;
    private String city;
    private String zipCode;

    public Address(String fullAddress, String city, String zipCode) {
        this.fullAddress = fullAddress;
        this.city = city;
        this.zipCode = zipCode;
    }
}
