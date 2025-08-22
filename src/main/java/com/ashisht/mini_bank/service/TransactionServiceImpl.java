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
import java.util.ArrayList;
import java.util.List;

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
    private final PaymentDischargeService paymentDischargeService;

    public TransactionServiceImpl(TransactionRepo transactionRepo, AccountRepo accountRepo, OperationTypeRepo operationTypeRepo, PaymentDischargeService paymentDischargeService) {
        this.transactionRepo = transactionRepo;
        this.accountRepo = accountRepo;
        this.operationTypeRepo = operationTypeRepo;
        this.paymentDischargeService = paymentDischargeService;
    }

    @Override
    public Transaction createTransaction(Long accountId, Integer operationTypeId, Double amount) {
        Account account = accountRepo.findById(accountId).orElseThrow(() -> new IllegalArgumentException("Account not found"));
        OperationType operationType = operationTypeRepo.findById(operationTypeId).orElseThrow(() -> new IllegalArgumentException("Operation type not found"));
        String desc = operationType.getDescription().toUpperCase();
        List<Transaction> allTransactionByAccount = transactionRepo.getAllTransactionByAccount(accountId);
        Transaction transaction = getTransaction(amount, desc, account, operationType);
        if (allTransactionByAccount != null) {
            allTransactionByAccount.add(transaction);
        } else {
            allTransactionByAccount = new ArrayList<>();
            allTransactionByAccount.add(transaction);
        }
        List<Transaction> transactions = paymentDischargeService.calculateBalance(allTransactionByAccount, operationTypeId);
        transactionRepo.saveAllAndFlush(transactions);
        return transaction;
    }

    private static Transaction getTransaction(Double amount, String desc, Account account, OperationType operationType) {
        if (desc.contains("PURCHASE") || desc.contains("WITHDRAWAL")) {
            amount = -Math.abs(amount);
        } else if (desc.contains("PAYMENT")) {
            amount = Math.abs(amount);
        }
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setOperationType(operationType);
        transaction.setAmount(amount);
        transaction.setEventDate(Instant.now());
        return transaction;
    }
}
