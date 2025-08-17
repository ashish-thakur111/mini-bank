package com.ashisht.mini_bank.service;

import com.ashisht.mini_bank.entity.Transaction;
import java.math.BigDecimal;

public interface TransactionService {
    Transaction createTransaction(Long accountId, Long operationTypeId, BigDecimal amount);
}
