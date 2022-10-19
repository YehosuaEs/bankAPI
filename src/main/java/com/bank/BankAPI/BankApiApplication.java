package com.bank.BankAPI;

import com.bank.BankAPI.models.AccountHolder;
import com.bank.BankAPI.models.accounts.Account;
import com.bank.BankAPI.models.accounts.Checking;
import com.bank.BankAPI.models.accounts.Savings;
import com.bank.BankAPI.models.others.Address;
import com.bank.BankAPI.models.others.Money;
import com.bank.BankAPI.repositories.AccountHolderRepository;
import com.bank.BankAPI.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class BankApiApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(BankApiApplication.class, args);
    }


    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public void run(String... args) throws Exception {

        // accountHolderRepository.save(new AccountHolder("Mario", LocalDate.of(2010,1,12),new Address("Calabria 11", "Bcn", "02122"), null));
        AccountHolder accountHolder1 = new AccountHolder("Mario Es", LocalDate.of(2000, 1, 12), new Address("Calabria 11", "Bcn", "02122"), null);
        AccountHolder accountHolder2 = new AccountHolder("Dario Son", LocalDate.of(1986, 11, 7), new Address("Tucuran 21", "Morelia", "12992"), new Address("Manso 13", "Barcelona", "12313"));
        AccountHolder accountHolder3 = new AccountHolder("Francis Jojo", LocalDate.of(1970, 4, 23), new Address("San Pedro 21", "Vaticano", "10101"), new Address("G via 233", "Paris", "00022"));
        AccountHolder accountHolder4 = new AccountHolder("Sabina Some", LocalDate.of(2000, 4, 1), new Address("S. Von Goethe 21", "Munich", "10101"), new Address("Napoles 233", "Roma", "00022"));
        accountHolderRepository.saveAll(List.of(
                accountHolder1, accountHolder2, accountHolder3, accountHolder4
        ));

        //Checking checking1 = new Checking(new Money(new BigDecimal(1200)), "xxxx", accountHolder4, null);
        //Checking checking2 = new Checking(new Money(new BigDecimal(1200)), "1q1q1", accountHolder4, accountHolder3);
        //accountRepository.saveAll(List.of(
        //   checking1, checking2
        //));

    }
}
