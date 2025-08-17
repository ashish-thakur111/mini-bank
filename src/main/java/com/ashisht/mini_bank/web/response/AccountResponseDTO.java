package com.ashisht.mini_bank.web.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AccountResponseDTO {
    private Long id;
    private String documentNumber;
}
