package com.bank.BankAPI.repositories;

import com.bank.BankAPI.models.ThirdParty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThirdPartyRepository extends JpaRepository<ThirdParty, Long> {
}
