package com.bank.BankAPI.models.accounts;

import com.bank.BankAPI.models.AccountHolder;
import com.bank.BankAPI.models.enums.Status;
import com.bank.BankAPI.models.others.Money;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class StudentChecking extends Account {

    @Enumerated(EnumType.STRING)
    private Status stdCheckingStatus  = Status.ACTIVE;

    public StudentChecking(Money balance, String secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner) {
        super(balance, secretKey, primaryOwner, secondaryOwner);
    }

}
