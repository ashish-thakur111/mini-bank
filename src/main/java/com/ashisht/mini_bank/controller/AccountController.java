package com.ashisht.mini_bank.controller;

import com.ashisht.mini_bank.service.AccountService;
import com.ashisht.mini_bank.web.request.AccountDTO;
import com.ashisht.mini_bank.web.response.AccountResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountResponseDTO> createAccount(@RequestBody @Valid AccountDTO accountDTO) {
        AccountResponseDTO account = accountService.createAccount(accountDTO);
        return ResponseEntity.ok(account);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountResponseDTO> getAccountById(@PathVariable Long accountId) {
        AccountResponseDTO accountById = accountService.getAccountById(accountId);
        return ResponseEntity.ok(accountById);
    }
}
