package com.ashisht.mini_bank.controller;

import com.ashisht.mini_bank.entity.Account;
import com.ashisht.mini_bank.mapper.AccountMapper;
import com.ashisht.mini_bank.service.AccountService;
import com.ashisht.mini_bank.web.request.AccountDTO;
import com.ashisht.mini_bank.web.response.AccountResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing accounts.
 * <p>
 * Exposes endpoints for creating and retrieving accounts. Handles DTO validation and mapping.
 */

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountResponseDTO> createAccount(@RequestBody @Valid AccountDTO accountDTO) {
        // Extract documentNumber from DTO and pass to service
        Account account = accountService.createAccount(accountDTO.getDocumentNumber());
        AccountResponseDTO response = AccountMapper.toAccountResponseDTO(account);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountResponseDTO> getAccountById(@PathVariable Long accountId) {
        Account account = accountService.getAccountById(accountId);
        AccountResponseDTO response = AccountMapper.toAccountResponseDTO(account);
        return ResponseEntity.ok(response);
    }
}
