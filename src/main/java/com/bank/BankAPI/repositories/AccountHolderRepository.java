package com.bank.BankAPI.repositories;

import com.bank.BankAPI.models.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface AccountHolderRepository  extends JpaRepository<AccountHolder, Long> {

    //Optional<AccountHolder>findByBirthDateId(Long id);
}