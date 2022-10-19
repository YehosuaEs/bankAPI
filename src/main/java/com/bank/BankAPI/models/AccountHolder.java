package com.bank.BankAPI.models;

import com.bank.BankAPI.models.accounts.Account;
import com.bank.BankAPI.models.others.Address;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class AccountHolder extends User {

    private LocalDate birthDate;

    @Embedded
    private Address mainAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "fullAddress", column = @Column(name = "full_address_secondary")),
            @AttributeOverride(name = "city", column = @Column(name = "city_secondary")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "zip_code_secondary"))})
    private Address secondaryAddress;


    @OneToMany(mappedBy = "primaryOwner")
    @JsonIgnore
    Set<Account> primaryAccounts;

    @OneToMany(mappedBy = "secondaryOwner")
    @JsonIgnore
    Set<Account> secondaryAccounts;

    public AccountHolder(String name, LocalDate birthDate, Address mainAddress, Address secondaryAddress) {
        super(name);
        this.birthDate = birthDate;
        this.mainAddress = mainAddress;
        this.secondaryAddress = secondaryAddress;
    }
}
