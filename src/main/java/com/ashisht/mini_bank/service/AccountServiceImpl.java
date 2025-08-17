package com.ashisht.mini_bank.service;

import com.ashisht.mini_bank.entity.Account;
import com.ashisht.mini_bank.repository.AccountRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Implementation of AccountService for handling account business logic.
 * <p>
 * Responsible for creating and retrieving accounts in the system.
 */
@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepo accountRepo;

    public AccountServiceImpl(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    @Override
    public Account createAccount(String documentNumber) {
        Account account = new Account();
        account.setDocumentNumber(documentNumber);
        return accountRepo.save(account);
    }

    @Override
    public Account getAccountById(Long accountId) {
        // Fetch the account by ID from the repository
        return accountRepo.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("Account not found with ID: " + accountId));
    }
}
