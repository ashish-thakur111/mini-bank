package com.ashisht.mini_bank.service;

import com.ashisht.mini_bank.entity.Transaction;

import java.util.List;

public interface PaymentDischargeService {

    public List<Transaction> calculateBalance(List<Transaction> transactionList, Integer operationId);
}
