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
    private String streetAndNum;
    private String city;
    private String zipCode;
}
