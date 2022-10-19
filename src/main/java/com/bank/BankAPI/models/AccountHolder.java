package com.bank.BankAPI.models;

import com.bank.BankAPI.models.accounts.Account;
import com.bank.BankAPI.models.others.Address;
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
            @AttributeOverride(name = "streetAndNum", column = @Column(name = "street_and_num_secondary")),
            @AttributeOverride(name = "city", column = @Column(name = "city_secondary")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "zip_code_secondary"))})
    private Address secondaryAddress;


    @OneToMany(mappedBy = "primaryOwner")
    Set<Account> primaryAccounts;

    @OneToMany(mappedBy = "secondaryOwner")
    Set<Account> secondaryAccounts;


}
