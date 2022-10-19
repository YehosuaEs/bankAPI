package com.bank.BankAPI.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Admins extends User{

    public Admins(String name) {
        super(name);
    }
}
