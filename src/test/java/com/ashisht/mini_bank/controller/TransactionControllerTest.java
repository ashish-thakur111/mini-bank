package com.ashisht.mini_bank.controller;

import com.ashisht.mini_bank.entity.Account;
import com.ashisht.mini_bank.entity.OperationType;
import com.ashisht.mini_bank.entity.Transaction;
import com.ashisht.mini_bank.service.TransactionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TransactionController.class)
class TransactionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TransactionService transactionService;

    @Test
    @DisplayName("POST /transactions - success")
    void createTransaction_success() throws Exception {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(1L);
        transaction.setAccount(Account.builder().accountId(1L).build());
        transaction.setOperationType(OperationType.builder().operationTypeId(1).description("CASH PURCHASE").build());
        when(transactionService.createTransaction(anyLong(), anyInt(), any(Double.class))).thenReturn(transaction);

        String requestBody = "{\"accountId\":1,\"operationTypeId\":1,\"amount\":100.0}";
        mockMvc.perform(post("/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.transactionId").value(1L));
    }

    @Test
    @DisplayName("POST /transactions - invalid input")
    void createTransaction_invalidInput() throws Exception {
        when(transactionService.createTransaction(anyLong(), eq(999), any(Double.class)))
                .thenThrow(new IllegalArgumentException("Operation type not found"));

        String requestBody = "{\"accountId\":1,\"operationTypeId\":999,\"amount\":100.0}";
        mockMvc.perform(post("/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Operation type not found"));
    }
}
