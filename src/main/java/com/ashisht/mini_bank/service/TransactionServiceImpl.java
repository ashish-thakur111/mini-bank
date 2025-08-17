package com.ashisht.mini_bank.service;

import com.ashisht.mini_bank.entity.Account;
import com.ashisht.mini_bank.entity.OperationType;
import com.ashisht.mini_bank.entity.Transaction;
import com.ashisht.mini_bank.repository.AccountRepo;
import com.ashisht.mini_bank.repository.OperationTypeRepo;
import com.ashisht.mini_bank.repository.TransactionRepo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Implementation of TransactionService for handling transaction business logic.
 * <p>
 * Responsible for creating and managing transactions in the system.
 */

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepo transactionRepo;
    private final AccountRepo accountRepo;
    private final OperationTypeRepo operationTypeRepo;

    public TransactionServiceImpl(TransactionRepo transactionRepo, AccountRepo accountRepo, OperationTypeRepo operationTypeRepo) {
        this.transactionRepo = transactionRepo;
        this.accountRepo = accountRepo;
        this.operationTypeRepo = operationTypeRepo;
    }

    @Override
    public Transaction createTransaction(Long accountId, Integer operationTypeId, BigDecimal amount) {
        Account account = accountRepo.findById(accountId).orElseThrow(() -> new IllegalArgumentException("Account not found"));
        OperationType operationType = operationTypeRepo.findById(operationTypeId).orElseThrow(() -> new IllegalArgumentException("Operation type not found"));
        String desc = operationType.getDescription().toUpperCase();
        Transaction transaction = getTransaction(amount, desc, account, operationType);
        return transactionRepo.save(transaction);
    }

    private static Transaction getTransaction(BigDecimal amount, String desc, Account account, OperationType operationType) {
        if (desc.contains("PURCHASE") || desc.contains("WITHDRAWAL")) {
            amount = amount.abs().negate();
        } else if (desc.contains("PAYMENT")) {
            amount = amount.abs();
        }
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setOperationType(operationType);
        transaction.setAmount(amount);
        transaction.setEventDate(Instant.now());
        return transaction;
    }
}
