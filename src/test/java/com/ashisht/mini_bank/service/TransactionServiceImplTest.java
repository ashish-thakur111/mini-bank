package com.ashisht.mini_bank.service;

import com.ashisht.mini_bank.entity.Account;
import com.ashisht.mini_bank.entity.OperationType;
import com.ashisht.mini_bank.entity.Transaction;
import com.ashisht.mini_bank.repository.AccountRepo;
import com.ashisht.mini_bank.repository.OperationTypeRepo;
import com.ashisht.mini_bank.repository.TransactionRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceImplTest {

    @Mock
    private TransactionRepo transactionRepo;
    @Mock
    private AccountRepo accountRepo;
    @Mock
    private OperationTypeRepo operationTypeRepo;

    private TransactionServiceImpl transactionService;

    @BeforeEach
    void setUp() {
        transactionService = new TransactionServiceImpl(transactionRepo, accountRepo, operationTypeRepo);
    }

    @Test
    void createTransaction_WithPurchase_ShouldCreateNegativeAmount() {
        // Arrange
        Account account = new Account();
        account.setAccountId(1L);

        OperationType operationType = new OperationType();
        operationType.setOperationTypeId(1L);
        operationType.setDescription("PURCHASE");

        Transaction expectedTransaction = new Transaction();
        expectedTransaction.setAmount(new BigDecimal("-100.00"));

        when(accountRepo.findById(1L)).thenReturn(Optional.of(account));
        when(operationTypeRepo.findById(1L)).thenReturn(Optional.of(operationType));
        when(transactionRepo.save(any(Transaction.class))).thenReturn(expectedTransaction);

        // Act
        Transaction result = transactionService.createTransaction(1L, 1L, new BigDecimal("100.00"));

        // Assert
        assertThat(new BigDecimal("-100.00")).isEqualTo(result.getAmount());
    }

    @Test
    void createTransaction_WithPayment_ShouldCreatePositiveAmount() {
        // Arrange
        Account account = new Account();
        account.setAccountId(1L);

        OperationType operationType = new OperationType();
        operationType.setOperationTypeId(2L);
        operationType.setDescription("PAYMENT");

        Transaction expectedTransaction = new Transaction();
        expectedTransaction.setAmount(new BigDecimal("100.00"));

        when(accountRepo.findById(1L)).thenReturn(Optional.of(account));
        when(operationTypeRepo.findById(2L)).thenReturn(Optional.of(operationType));
        when(transactionRepo.save(any(Transaction.class))).thenReturn(expectedTransaction);

        // Act
        Transaction result = transactionService.createTransaction(1L, 2L, new BigDecimal("100.00"));

        // Assert
        assertThat(new BigDecimal("100.00")).isEqualTo(result.getAmount());
    }

    @Test
    void createTransaction_WithInvalidAccount_ShouldThrowException() {
        // Arrange
        when(accountRepo.findById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> transactionService.createTransaction(999L, 1L, new BigDecimal("100.00")))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void createTransaction_WithInvalidOperationType_ShouldThrowException() {
        // Arrange
        Account account = new Account();
        account.setAccountId(1L);

        when(accountRepo.findById(1L)).thenReturn(Optional.of(account));
        when(operationTypeRepo.findById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> transactionService.createTransaction(1L, 999L, new BigDecimal("100.00")))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
