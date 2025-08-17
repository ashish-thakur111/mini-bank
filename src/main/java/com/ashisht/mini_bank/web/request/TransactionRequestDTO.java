package com.ashisht.mini_bank.web.request;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.DecimalMin;

@Getter
@Setter
public class TransactionRequestDTO {
    @NotNull(message = "accountId is required")
    @Positive(message = "accountId must be positive")
    private Long accountId;

    @NotNull(message = "operationTypeId is required")
    @Positive(message = "operationTypeId must be positive")
    private Long operationTypeId;

    @NotNull(message = "amount is required")
    @DecimalMin(value = "0.01", message = "amount must be greater than 0")
    private BigDecimal amount;
}
