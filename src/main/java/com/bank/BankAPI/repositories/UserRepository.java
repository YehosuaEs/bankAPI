package com.bank.BankAPI.repositories;

import com.bank.BankAPI.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
