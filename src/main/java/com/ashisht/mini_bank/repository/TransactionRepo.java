package com.ashisht.mini_bank.repository;

import com.ashisht.mini_bank.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {
}

