package com.ashisht.mini_bank.web.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AccountDTO {
    @NotBlank
    private String documentNumber;
}
