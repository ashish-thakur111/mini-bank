package com.ashisht.mini_bank.repository;

import com.ashisht.mini_bank.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {
    @Query(value = "select * from transaction where account_id=?", nativeQuery = true)
    List<Transaction> getAllTransactionByAccount(Long accountId);
}

