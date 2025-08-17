package com.ashisht.mini_bank.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashisht.mini_bank.entity.Transaction;
import com.ashisht.mini_bank.service.TransactionService;
import com.ashisht.mini_bank.web.request.TransactionRequestDTO;
import com.ashisht.mini_bank.web.response.TransactionDTO;
import com.ashisht.mini_bank.mapper.TransactionMapper;

import jakarta.validation.Valid;

/**
 * REST controller for managing transactions.
 * <p>
 * Exposes endpoints for creating transactions and delegates business logic to the TransactionService.
 * Handles mapping between request/response DTOs and domain entities.
 */

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody @Valid TransactionRequestDTO request) {
        Transaction transaction = transactionService.createTransaction(
            request.getAccountId(),
            request.getOperationTypeId(),
            request.getAmount()
        );
        TransactionDTO response = TransactionMapper.toTransactionDTO(transaction, "SUCCESS");
        return ResponseEntity.ok(response);
    }
}
