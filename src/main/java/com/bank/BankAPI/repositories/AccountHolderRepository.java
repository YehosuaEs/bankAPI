package com.bank.BankAPI.repositories;

import com.bank.BankAPI.models.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountHolderRepository  extends JpaRepository<AccountHolder, Long> {
}
