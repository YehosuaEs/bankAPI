package com.bank.BankAPI.models.accounts;

import com.bank.BankAPI.models.AccountHolder;
import com.bank.BankAPI.models.others.Money;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class CreditCard extends Account {
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "credit_limit_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "credit_limit_amount"))})
    private Money creditLimit = new Money(BigDecimal.valueOf(100)); //creditLimit higher than 100 but not higher than 100000

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "currency", column = @Column(name = "interest_rate_cc_currency")),
            @AttributeOverride(name = "amount", column = @Column(name = "interest_rate_cc_amount"))})
    private Money interestRate = new Money(BigDecimal.valueOf(0.2)); //interestRate less than 0.2 but not lower than 0.1
    //private LocalDate interestRateCCDate;


    public CreditCard(Money balance, String secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner, Money creditLimit, Money interestRate) {
        super(balance, secretKey, primaryOwner, secondaryOwner);
        setCreditLimit(creditLimit);
        setInterestRate(interestRate);
    }

    // ----------- Setting the creditLimit not more than 100000
    public void setCreditLimit(Money creditLimit) {
        if (creditLimit.getAmount().doubleValue() < 100 || creditLimit.getAmount().doubleValue() > 100000) {
            throw new IllegalArgumentException("Credit Limit should  be between 100 and 100000");
        } else {
            this.creditLimit = creditLimit;
        }
    }

    // ----------- Setting the interestRateCC not lower than 0.1
    public void setInterestRate(Money interestRate) {
        if (interestRate.getAmount().doubleValue() < 0.1 || interestRate.getAmount().doubleValue() > 0.2) {
            throw new IllegalArgumentException("Interest Rate should be between 0.1 and 0.2");
        } else {
            this.interestRate = interestRate;
        }
    }

    public Money getInterestRate() {
        return interestRate;
    }

    //METODS interestRate
    /*Interest on credit cards is added to the balance monthly.
    If you have a 12% interest rate (0.12) then 1% interest will be added to the account monthly.
    When the balance of a credit card is accessed, check to determine
    if it has been 1 month or more since the account was created or since
    interested was added, and if so, add the appropriate interest to the balance.*/
    /*@Override
    public void setBalance(Money balance) {
        super.setBalance(balance);
    }*/
    public void addInterestRateCCtoBalance() {
        LocalDate now = LocalDate.now();
        java.time.Period period = java.time.Period.between(super.getCreationDate(), now);
        int months = period.getYears() * 12 + period.getMonths();
        //BigDecimal roundInterestRate = roundedNum(BigDecimal.valueOf(getInterestRateCC().getAmount().doubleValue())).multiply(BigDecimal.valueOf(months));
        //super.setBalance(new Money(getBalance().getAmount()));
        BigDecimal roundInterestRate = roundedNum(BigDecimal.valueOf(getInterestRate().getAmount().doubleValue()));
        BigDecimal amountBalance = new Money(BigDecimal.valueOf(super.getBalance().getAmount().doubleValue())).getAmount(); //Si se puede acceder al al getBalance así?
        Money totalInterestRateInTime = new Money(amountBalance.add(amountBalance).multiply(roundInterestRate)); // si se castean los big decimals de esta forma?
        for (int i = 0; i < months; i++) {
            super.setBalance(totalInterestRateInTime);
        }
    }


    // Metodo para obtener el redondeo del interesRate
    public static BigDecimal roundedNum(BigDecimal num){
        return num.setScale(1, RoundingMode.HALF_EVEN);
    }


}
