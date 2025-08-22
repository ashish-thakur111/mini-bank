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
    @Mock
    private PaymentDischargeService paymentDischargeService;

    private TransactionServiceImpl transactionService;

    @BeforeEach
    void setUp() {
        transactionService = new TransactionServiceImpl(transactionRepo, accountRepo, operationTypeRepo, paymentDischargeService);
    }

    @Test
    void createTransaction_WithPurchase_ShouldCreateNegativeAmount() {
        // Arrange
        Account account = new Account();
        account.setAccountId(1L);

        OperationType operationType = new OperationType();
        operationType.setOperationTypeId(1);
        operationType.setDescription("PURCHASE");

        Transaction expectedTransaction = new Transaction();
        expectedTransaction.setAmount(-100.00);

        when(accountRepo.findById(1L)).thenReturn(Optional.of(account));
        when(operationTypeRepo.findById(1)).thenReturn(Optional.of(operationType));
        when(transactionRepo.getAllTransactionByAccount(1L)).thenReturn(new java.util.ArrayList<>());
        when(transactionRepo.saveAllAndFlush(any())).thenReturn(null);

        // Act
        Transaction result = transactionService.createTransaction(1L, 1, -100.00);

        // Assert
        assertThat(result.getAmount())
            .as("Amount should be negative for PURCHASE operation")
            .isEqualTo(-100.00);
    }

    @Test
    void createTransaction_WithPayment_ShouldCreatePositiveAmount() {
        // Arrange
        Account account = new Account();
        account.setAccountId(1L);

        OperationType operationType = new OperationType();
        operationType.setOperationTypeId(2);
        operationType.setDescription("PAYMENT");

        Transaction expectedTransaction = new Transaction();
        expectedTransaction.setAmount(100.00);

        when(accountRepo.findById(1L)).thenReturn(Optional.of(account));
        when(operationTypeRepo.findById(2)).thenReturn(Optional.of(operationType));
        when(transactionRepo.getAllTransactionByAccount(1L)).thenReturn(new java.util.ArrayList<>());
        when(transactionRepo.saveAllAndFlush(any())).thenReturn(null);

        // Act
        Transaction result = transactionService.createTransaction(1L, 2, 100.00);

        // Assert
        assertThat(result.getAmount())
            .as("Amount should be positive for PAYMENT operation")
            .isEqualTo(100.00);
    }

    @Test
    void createTransaction_WithInvalidAccount_ShouldThrowException() {
        // Arrange
        when(accountRepo.findById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> transactionService.createTransaction(999L, 1, 100.00))
            .as("Should throw IllegalArgumentException for invalid account")
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void createTransaction_WithInvalidOperationType_ShouldThrowException() {
        // Arrange
        Account account = new Account();
        account.setAccountId(1L);

        when(accountRepo.findById(1L)).thenReturn(Optional.of(account));
        when(operationTypeRepo.findById(999)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> transactionService.createTransaction(1L, 999, 100.00))
            .as("Should throw IllegalArgumentException for invalid operation type")
            .isInstanceOf(IllegalArgumentException.class);
    }
}
