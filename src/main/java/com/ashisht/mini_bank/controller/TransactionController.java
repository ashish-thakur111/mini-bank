package com.ashisht.mini_bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashisht.mini_bank.entity.Transaction;
import com.ashisht.mini_bank.service.TransactionService;
import com.ashisht.mini_bank.web.request.TransactionRequestDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<?> createTransaction(@RequestBody @Valid TransactionRequestDTO request) {
        Transaction transaction = transactionService.createTransaction(
            request.getAccountId(),
            request.getOperationTypeId(),
            request.getAmount()
        );
        return ResponseEntity.ok(transaction);
    }
}
