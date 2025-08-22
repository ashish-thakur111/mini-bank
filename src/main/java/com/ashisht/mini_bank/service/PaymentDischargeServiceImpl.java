package com.ashisht.mini_bank.service;

import com.ashisht.mini_bank.entity.Transaction;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class PaymentDischargeServiceImpl implements PaymentDischargeService {
    @Override
    public List<Transaction> calculateBalance(List<Transaction> transactionList, Integer operationId) {
        transactionList.sort(Comparator.comparing(Transaction::getEventDate));
        List<Transaction> negativeTransactions = transactionList.stream().filter(t -> t.getAmount() < 0).toList();
        List<Transaction> creditTransactions = transactionList.stream().filter(t -> t.getAmount() > 0).toList();
        double creditBal = 0;
        Transaction last = null;
        if (!creditTransactions.isEmpty()) {
            last = creditTransactions.getLast();
            if (operationId == 4) {
                Transaction secondLast = null;
                if (creditTransactions.size() >= 2) {
                    secondLast = creditTransactions.get(creditTransactions.size() - 2);
                }
                if (last.getBalance() == null) {
                    last.setBalance(last.getAmount());
                }

                if (creditTransactions.size() >= 2) {
                    creditBal = last.getBalance() + secondLast.getBalance();
                } else {
                    creditBal = last.getBalance();
                }
            } else {
                creditBal = last.getBalance();
            }
        }
        // Update negative transactions with remaining balance
        for (Transaction nt : negativeTransactions) {
            if (nt.getBalance() == null) {
                nt.setBalance(nt.getAmount());
            }
            // Skip transactions that already have zero balance
            if (creditBal > 0 && (nt.getBalance() == null || nt.getBalance() != 0)) {
                double negativeAmount = Math.abs(nt.getBalance());
                if (negativeAmount <= creditBal) {
                    nt.setBalance(0.0);
                    creditBal -= negativeAmount;
                } else {
                    nt.setBalance(-(negativeAmount - creditBal));
                    creditBal = 0;
                }
            }
        }

        if (last != null) {
            last.setBalance(creditBal);
        }

        // Update final balance for any remaining negative transactions
        for (Transaction tx : negativeTransactions) {
            if (tx.getBalance() == null) {
                tx.setBalance(tx.getAmount());
            }
        }

        return transactionList;
    }
}
