package com.ashisht.mini_bank.service;

import com.ashisht.mini_bank.entity.Transaction;
import org.junit.jupiter.api.Test;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

class PaymentDischargeServiceImplTest {
    private final PaymentDischargeServiceImpl service = new PaymentDischargeServiceImpl();

    private Transaction tx(double amount, Double balance, Instant eventDate) {
        Transaction t = new Transaction();
        t.setAmount(amount);
        t.setBalance(balance);
        t.setEventDate(eventDate);
        return t;
    }

    @Test
    void testOnlyCreditTransactions() {
        Transaction t1 = tx(100.0, 100.0, Instant.parse("2025-08-20T10:00:00Z"));
        Transaction t2 = tx(200.0, 200.0, Instant.parse("2025-08-21T10:00:00Z"));
        List<Transaction> result = service.calculateBalance(Arrays.asList(t1, t2), 1);
        assertThat(result).allMatch(t -> t.getBalance() != null);
        assertThat(result.get(1).getBalance()).isEqualTo(200.0);
    }

    @Test
    void testOnlyNegativeTransactions() {
        Transaction t1 = tx(-50.0, null, Instant.parse("2025-08-20T10:00:00Z"));
        Transaction t2 = tx(-30.0, null, Instant.parse("2025-08-21T10:00:00Z"));
        List<Transaction> result = service.calculateBalance(Arrays.asList(t1, t2), 1);
        assertThat(result).allMatch(t -> t.getBalance() != null);
        assertThat(result.get(0).getBalance()).isEqualTo(-50.0);
        assertThat(result.get(1).getBalance()).isEqualTo(-30.0);
    }

    @Test
    void testMixedTransactionsOperationIdNot4() {
        Transaction t1 = tx(-50.0, null, Instant.parse("2025-08-20T10:00:00Z"));
        Transaction t2 = tx(100.0, 100.0, Instant.parse("2025-08-21T10:00:00Z"));
        List<Transaction> result = service.calculateBalance(Arrays.asList(t1, t2), 1);
        assertThat(result.get(0).getBalance()).isEqualTo(0.0);
        assertThat(result.get(1).getBalance()).isEqualTo(50.0);
    }

    @Test
    void testMixedTransactionsOperationId4() {
        Transaction t1 = tx(-50.0, null, Instant.parse("2025-08-20T10:00:00Z"));
        Transaction t2 = tx(100.0, 100.0, Instant.parse("2025-08-21T10:00:00Z"));
        Transaction t3 = tx(30.0, 30.0, Instant.parse("2025-08-22T10:00:00Z"));
        List<Transaction> result = service.calculateBalance(Arrays.asList(t1, t2, t3), 4);
        assertThat(result.get(0).getBalance()).isEqualTo(0.0);
        assertThat(result.get(2).getBalance()).isEqualTo(80.0);
    }

    @Test
    void testZeroBalanceAndNullBalanceHandling() {
        Transaction t1 = tx(-50.0, 0.0, Instant.parse("2025-08-20T10:00:00Z"));
        Transaction t2 = tx(100.0, 100.0, Instant.parse("2025-08-21T10:00:00Z"));
        List<Transaction> result = service.calculateBalance(Arrays.asList(t1, t2), 1);
        assertThat(result.get(0).getBalance()).isEqualTo(0.0);
        assertThat(result.get(1).getBalance()).isEqualTo(100.0);
    }

    @Test
    void testOperationId4WithSingleCreditTransaction() {
        Transaction t1 = tx(-30.0, null, Instant.parse("2025-08-20T10:00:00Z"));
        Transaction t2 = tx(100.0, 100.0, Instant.parse("2025-08-21T10:00:00Z"));
        List<Transaction> result = service.calculateBalance(Arrays.asList(t1, t2), 4);
        assertThat(result.get(0).getBalance()).isEqualTo(0.0);
        assertThat(result.get(1).getBalance()).isEqualTo(70.0);
    }

    @Test
    void testMultipleNegativeTransactionsWithInsufficientCredit() {
        Transaction t1 = tx(-50.0, null, Instant.parse("2025-08-20T10:00:00Z"));
        Transaction t2 = tx(-30.0, null, Instant.parse("2025-08-21T10:00:00Z"));
        Transaction t3 = tx(60.0, 60.0, Instant.parse("2025-08-22T10:00:00Z"));
        List<Transaction> result = service.calculateBalance(Arrays.asList(t1, t2, t3), 1);
        assertThat(result.get(0).getBalance()).isEqualTo(0.0);
        assertThat(result.get(1).getBalance()).isEqualTo(-20.0);
        assertThat(result.get(2).getBalance()).isEqualTo(0.0);
    }
}
