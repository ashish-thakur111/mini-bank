package com.ashisht.mini_bank.repository;


import com.ashisht.mini_bank.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepo extends JpaRepository<Account, Long> {
}
