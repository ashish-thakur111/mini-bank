package com.ashisht.mini_bank.service;

import com.ashisht.mini_bank.entity.Account;
import com.ashisht.mini_bank.mapper.AccountMapper;
import com.ashisht.mini_bank.repository.AccountRepo;
import com.ashisht.mini_bank.web.request.AccountDTO;
import com.ashisht.mini_bank.web.response.AccountResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepo accountRepo;

    public AccountServiceImpl(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    @Override
    public AccountResponseDTO createAccount(AccountDTO accountDTO) {
        // Create a new account entity
        Account account = AccountMapper.toAccountEntity(accountDTO);

        // Save the account to the repository
        Account savedAccount = accountRepo.save(account);

        // Convert the saved account to a response DTO
        return AccountMapper.toAccountResponseDTO(savedAccount);
    }

    @Override
    public AccountResponseDTO getAccountById(Long accountId) {
        // Fetch the account by ID from the repository
        Account account = accountRepo.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("Account not found with ID: " + accountId));

        // Convert the account to a response DTO
        return AccountMapper.toAccountResponseDTO(account);
    }
}
