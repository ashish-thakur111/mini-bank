package com.ashisht.mini_bank.service;

import com.ashisht.mini_bank.entity.Transaction;
import java.math.BigDecimal;

/**
 * Service interface for transaction-related business logic.
 * <p>
 * Defines contract for creating transactions and other transaction operations.
 */

public interface TransactionService {
    /**
     * Creates a new transaction for the specified account and operation type.
     *
     * @param accountId        The ID of the account associated with the transaction.
     * @param operationTypeId  The ID of the operation type for the transaction.
     * @param amount           The amount involved in the transaction.
     * @return The created Transaction entity.
     */
    Transaction createTransaction(Long accountId, Integer operationTypeId, BigDecimal amount);
}
