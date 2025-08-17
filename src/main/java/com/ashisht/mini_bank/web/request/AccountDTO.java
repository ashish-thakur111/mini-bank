package com.ashisht.mini_bank.web.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

/**
 * Data Transfer Object (DTO) for account creation requests.
 * <p>
 * Encapsulates the data required to create a new account via the API.
 * </p>
 */
@Builder
@Data
public class AccountDTO {
    @NotBlank
    private String documentNumber;
}
