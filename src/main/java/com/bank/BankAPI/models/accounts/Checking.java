package com.bank.BankAPI.models.accounts;

import com.bank.BankAPI.models.AccountHolder;
import com.bank.BankAPI.models.enums.Status;
import com.bank.BankAPI.models.others.Money;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Checking extends Account {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "min_balance_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "min_balance_amount"))})
    private  Money minBalance = new Money(BigDecimal.valueOf(250));

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "month_maintain_fee_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "month_maintain_fee_amount"))})
    private final Money monthMaintainFee = new Money(BigDecimal.valueOf(12));
    //private LocalDate maintainFeeDate;

    @Enumerated(EnumType.STRING)
    private Status checkingStatus = Status.ACTIVE;


    public Checking(Money balance, String secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner) {
        super(balance, secretKey, primaryOwner, secondaryOwner);
    }

    /*If any account drops below the minimumBalance,
    the penaltyFee should be deducted from the balance automatically*/
    @Override
    public void setBalance(Money balance) {
        if (minBalance == null) {
            minBalance = new Money(BigDecimal.valueOf(250));
        }
        if(balance.getAmount().compareTo(minBalance.getAmount()) < 0){
            balance.getAmount().subtract(getPenaltyFee().getAmount());
        }
        super.setBalance(balance);
    }


    /*
     When creating a new Checking account, if the primaryOwner is less than 24,
     a StudentChecking account should be created
     otherwise a regular Checking Account should be created.
     // HACERLO EN SERVIE... CON UN DTO
    */

}
