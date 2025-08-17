package com.ashisht.mini_bank.web.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Data Transfer Object (DTO) for transaction API responses.
 * <p>
 * Encapsulates transaction details returned to the client after transaction creation.
 */

@Builder
@Data
public class TransactionDTO {
    private Long transactionId;
    private Long accountId;
    private Integer operationTypeId;
    private BigDecimal amount;
    private String message;
}
