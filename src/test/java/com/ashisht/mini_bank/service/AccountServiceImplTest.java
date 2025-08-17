package com.ashisht.mini_bank.service;

import com.ashisht.mini_bank.entity.Account;
import com.ashisht.mini_bank.mapper.AccountMapper;
import com.ashisht.mini_bank.repository.AccountRepo;
import com.ashisht.mini_bank.web.request.AccountDTO;
import com.ashisht.mini_bank.web.response.AccountResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {
    @Mock
    private AccountRepo accountRepo;
    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    void createAccount_shouldSaveAndReturnResponse() {
        Account account = new Account();
        Account savedAccount = new Account();
        savedAccount.setAccountId(1L);
        savedAccount.setDocumentNumber("12345678900");

        when(accountRepo.save(account)).thenReturn(savedAccount);

        Account result = accountService.createAccount("12345678900");
        assertThat(result).isEqualTo(savedAccount);
    }

    @Test
    void getAccountById_shouldReturnResponse_whenAccountExists() {
        Long id = 1L;
        Account account = new Account();
        account.setAccountId(1L);
        account.setDocumentNumber("12345678900");

        when(accountRepo.findById(id)).thenReturn(Optional.of(account));
        Account result = accountService.getAccountById(id);
        assertThat(result).isEqualTo(account);
    }

    @Test
    void getAccountById_shouldThrow_whenAccountNotFound() {
        Long id = 2L;
        when(accountRepo.findById(id)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> accountService.getAccountById(id))
            .isInstanceOf(EntityNotFoundException.class);
    }
}
