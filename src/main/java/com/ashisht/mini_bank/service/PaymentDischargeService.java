package com.ashisht.mini_bank.service;

import com.ashisht.mini_bank.entity.Transaction;

import java.util.List;

public interface PaymentDischargeService {

    public double calculateBalance(List<Transaction> transactionList);
}
