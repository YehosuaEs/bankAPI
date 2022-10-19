package com.bank.BankAPI.repositories;

import com.bank.BankAPI.models.accounts.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
