package com.ashisht.mini_bank.service;

import com.ashisht.mini_bank.entity.Transaction;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

@Service
public class PaymentDischargeServiceImpl implements PaymentDischargeService {
    @Override
    public double calculateBalance(List<Transaction> transactionList) {
        List<Double> negativeBalances = new ArrayList<>();
        List<Transaction> negativeTransactions = new ArrayList<>();
        double balance = 0.0;
        transactionList.sort(Comparator.comparing(Transaction::getEventDate));

        for(Transaction tx : transactionList) {
            if (tx.getAmount().doubleValue() < 0) {
                negativeBalances.add(tx.getAmount().doubleValue());
                negativeTransactions.add(tx);
            } else {
                double credit = tx.getAmount().doubleValue();


                double finalCredit = credit;
                double creditBal = 0.0;
                for (Transaction nt: negativeTransactions) {
                    if (nt.getAmount().abs().doubleValue() <= creditBal) {
                        nt.setBalance(BigDecimal.valueOf(0.0));
                        double ba = finalCredit - nt.getAmount().abs().doubleValue();
                        if (ba > 0.0) {
                            creditBal = ba;
                        }
                    }
                    if (nt.getAmount().abs().doubleValue() <= finalCredit) {
                        nt.setBalance(BigDecimal.valueOf(0.0));
                        double ba = finalCredit - nt.getAmount().abs().doubleValue();
                        if (ba > 0.0) {
                            creditBal = ba;
                        }
                    }
                    if (creditBal == 0) {
                        break;
                    }
                }
                Iterator<Double> iterator = negativeBalances.iterator();
                while (iterator.hasNext() && credit > 0) {
                    double debt = iterator.next();
                    double discharge = Math.min(credit, -debt);
                    credit -= discharge;
                    debt += discharge;
                    iterator.remove(); //remove from list
                    if (debt != 0) {
                        negativeBalances.addFirst(debt);
                    }
                }
                balance += credit;
            }
        }

        // sum up any remaining negative balances
        for (double debt : negativeBalances) {
            balance += debt;
        }
        return balance; // returning final balance
    }
}
