package com.ashisht.mini_bank.service;

import com.ashisht.mini_bank.entity.Account;
import com.ashisht.mini_bank.mapper.AccountMapper;
import com.ashisht.mini_bank.repository.AccountRepo;
import com.ashisht.mini_bank.web.request.AccountDTO;
import com.ashisht.mini_bank.web.response.AccountResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {
    @Mock
    private AccountRepo accountRepo;
    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    void createAccount_shouldSaveAndReturnResponse() {
        AccountDTO dto = AccountDTO.builder().documentNumber("12345678900").build();
        Account account = new Account();
        Account savedAccount = new Account();
        AccountResponseDTO responseDTO = AccountResponseDTO.builder().id(1L).documentNumber("12345678900").build();

        try (MockedStatic<AccountMapper> mockedMapper = mockStatic(AccountMapper.class)) {
            mockedMapper.when(() -> AccountMapper.toAccountEntity(dto)).thenReturn(account);
            when(accountRepo.save(account)).thenReturn(savedAccount);
            mockedMapper.when(() -> AccountMapper.toAccountResponseDTO(savedAccount)).thenReturn(responseDTO);

            AccountResponseDTO result = accountService.createAccount(dto);
            assertEquals(responseDTO, result);
        }
    }

    @Test
    void getAccountById_shouldReturnResponse_whenAccountExists() {
        Long id = 1L;
        Account account = new Account();
        AccountResponseDTO responseDTO = AccountResponseDTO.builder().id(1L).documentNumber("12345678900").build();

        when(accountRepo.findById(id)).thenReturn(Optional.of(account));
        try (MockedStatic<AccountMapper> mockedMapper = mockStatic(AccountMapper.class)) {
            mockedMapper.when(() -> AccountMapper.toAccountResponseDTO(account)).thenReturn(responseDTO);
            AccountResponseDTO result = accountService.getAccountById(id);
            assertEquals(responseDTO, result);
        }
    }

    @Test
    void getAccountById_shouldThrow_whenAccountNotFound() {
        Long id = 2L;
        when(accountRepo.findById(id)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> accountService.getAccountById(id));
    }
}
