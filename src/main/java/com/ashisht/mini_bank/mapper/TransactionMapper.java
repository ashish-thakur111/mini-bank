package com.ashisht.mini_bank.mapper;

import com.ashisht.mini_bank.entity.Transaction;
import com.ashisht.mini_bank.web.response.TransactionDTO;

/**
 * Mapper class for converting Transaction entities to TransactionDTOs.
 * <p>
 * Provides static methods to map domain entities to API response DTOs for transactions.
 */
public class TransactionMapper {
    /**
     * Converts a Transaction entity to a TransactionDTO with a custom message.
     *
     * @param transaction the Transaction entity to convert
     * @param message     the message to include in the DTO
     * @return a TransactionDTO containing transaction details and the provided message
     */
    public static TransactionDTO toTransactionDTO(Transaction transaction, String message) {
        return TransactionDTO.builder().transactionId(transaction.getTransactionId()).
            accountId(transaction.getAccount().getAccountId()).
            operationTypeId(transaction.getOperationType().getOperationTypeId()).
            amount(transaction.getAmount()).message(message)
            .build();
    }
}
