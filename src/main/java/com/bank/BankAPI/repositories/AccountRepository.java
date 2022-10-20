package com.bank.BankAPI.repositories;

import com.bank.BankAPI.models.AccountHolder;
import com.bank.BankAPI.models.accounts.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

public interface AccountRepository extends JpaRepository<Account, Long> {



}
