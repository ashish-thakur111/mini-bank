package com.ashisht.mini_bank.web.response;

import lombok.Builder;
import lombok.Data;

/**
 * Data Transfer Object (DTO) for account API responses.
 * <p>
 * Encapsulates account details returned to the client after account creation or retrieval.
 */

@Builder
@Data
public class AccountResponseDTO {
    private Long id;
    private String documentNumber;
}
