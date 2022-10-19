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
public class Savings extends Account {
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "minim_balance_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "minim_balance_amount"))})
    private Money minBalance = new Money(BigDecimal.valueOf(1000)); // 1000 but not lower than 100

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "interest_rate_s_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "interest_rate_s_amount"))})
    private Money interestRate = new Money(BigDecimal.valueOf(0.0025)); // With a max 0.5 Interest
    //private LocalDate interestRateDateS;

    @Enumerated(EnumType.STRING)
    private Status savingsStatus = Status.ACTIVE;

    //eliminar penalty fee del constructor
    public Savings(Money balance, String secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner, Money minBalance, Money interestRate) {
        super(balance, secretKey, primaryOwner, secondaryOwner);
        setMinBalance(minBalance);
        setInterestRate(interestRate);
    }

    public Savings(Money balance, String secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner, Money minBalance) {
        super(balance, secretKey, primaryOwner, secondaryOwner);
        this.minBalance = minBalance;
    }

    public Savings(Money balance, String secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner) {
        super(balance, secretKey, primaryOwner, secondaryOwner);
    }

    // ----------- Setting the minimBalance not lower than 100
    public void setMinBalance(Money minBalance) {
        //if (minimBalance.getAmount().compareTo(new BigDecimal(100)) < 0) {
        if (minBalance.getAmount().doubleValue() < 100) {
            System.err.println("Erro in the balance set");
            throw new IllegalArgumentException("Minimum balance should be greater than 100");
            //this.minimBalance = new Money(BigDecimal.valueOf(100));
        } else {
            this.minBalance = minBalance;
        }
    }

    // ----------- Setting the interesetRateSavings not more than 0.5
    public void setInterestRate(Money interestRate) {
        if (interestRate.getAmount().doubleValue() > 0.5 || interestRate.getAmount().doubleValue() < 0 ) {
            throw new IllegalArgumentException("Interest rate can't be greater than 0.5 and can't be negative");
            //this.interestRateS = new Money(BigDecimal.valueOf(0.5));
        } else {
            this.interestRate = interestRate;
        }
    }

    /*If any account drops below the minimumBalance, the penaltyFee
    should be deducted from the balance automatically*/
    @Override
    public void setBalance(Money balance) {
        if (minBalance == null) {
            minBalance = new Money(BigDecimal.valueOf(1000));
        }
        if(balance.getAmount().compareTo(minBalance.getAmount()) < 0){
            balance.getAmount().subtract(getPenaltyFee().getAmount());
        }
        super.setBalance(balance);
    }

    //Método para interestRate
    /*Interest on savings accounts is added to the account annually at the rate of specified
    interestRate per year. That means that if I have 1000000 in a savings account with a 0.01
    interest rate, 1% of 1 Million is added to my account after 1 year.
    When a savings account balance is accessed, you must determine if it has been 1 year or more
    since either the account was created or since interest was added to the account,
    and add the appropriate interest to the balance if necessary.*/



    // Metodo para MinimBalance PenaltyFee
    // ME ESTA DANDP PROBLEMAS
    /* @Override
    public void setBalance(Money balance) {
        if(balance.getAmount().compareTo(minimBalance.getAmount()) < 0){
            balance.getAmount().subtract(getPenaltyFee().getAmount());
        }
        super.setBalance(balance);
    }*/

}
