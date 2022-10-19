package com.bank.BankAPI.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ThirdParty extends User {

    private String hashedKey;

    public ThirdParty(String name, String hashedKey) {
        super(name);
       setHashedKey(hashedKey);
    }
}
