package com.ashisht.mini_bank.controller;

import com.ashisht.mini_bank.entity.Account;
import com.ashisht.mini_bank.service.AccountService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AccountController.class)
class AccountControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AccountService accountService;

    @Test
    @DisplayName("POST /accounts - success")
    void createAccount_success() throws Exception {
        Account account = createTestAccount(1L, "1234567890");
        when(accountService.createAccount(anyString())).thenReturn(account);

        String requestBody = "{\"documentNumber\":\"1234567890\"}";
        mockMvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.documentNumber").value("1234567890"));
    }

    @Test
    @DisplayName("GET /accounts/{id} - success")
    void getAccountById_success() throws Exception {
        Account account = createTestAccount(2L, "9876543210");
        when(accountService.getAccountById(2L)).thenReturn(account);

        mockMvc.perform(get("/accounts/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2L))
                .andExpect(jsonPath("$.documentNumber").value("9876543210"));
    }

    private static Account createTestAccount(Long id, String documentNumber) {
        return Account.builder()
            .accountId(id)
            .documentNumber(documentNumber)
            .build();
    }
}
