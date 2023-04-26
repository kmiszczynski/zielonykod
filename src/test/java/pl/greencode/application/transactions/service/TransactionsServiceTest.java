package pl.greencode.application.transactions.service;

import org.junit.jupiter.api.Test;
import pl.greencode.application.transactions.pojo.request.Transaction;
import pl.greencode.application.transactions.pojo.response.Account;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionsServiceTest {
    private final TransactionsService transactionsService = new TransactionsService();

    @Test
    public void shouldCreateReportWithCorrectValues() {
        // given
        List<Transaction> transactions = provideTransactions();
        List<Account> expectedAccounts = provideExpectedAccounts();

        // when
        List<Account> accounts = transactionsService.createReport(transactions);

        // then
        assertEquals(expectedAccounts, accounts);
    }

    private List<Account> provideExpectedAccounts() {
        return List.of(
                Account.builder()
                        .account("06105023389842834748547303")
                        .debitCount(0)
                        .creditCount(1)
                        .balance(new BigDecimal("10.90"))
                        .build(),
                Account.builder()
                        .account("31074318698137062235845814")
                        .debitCount(1)
                        .creditCount(0)
                        .balance(new BigDecimal("-200.90"))
                        .build(),
                Account.builder()
                        .account("32309111922661937852684864")
                        .debitCount(1)
                        .creditCount(1)
                        .balance(new BigDecimal("39.20"))
                        .build(),
                Account.builder()
                        .account("66105036543749403346524547")
                        .debitCount(1)
                        .creditCount(1)
                        .balance(new BigDecimal("150.80"))
                        .build()
        );
    }

    private List<Transaction> provideTransactions() {
        return List.of(
                Transaction.builder()
                        .debitAccount("32309111922661937852684864")
                        .creditAccount("06105023389842834748547303")
                        .amount(new BigDecimal("10.90"))
                        .build(),
                Transaction.builder()
                        .debitAccount("31074318698137062235845814")
                        .creditAccount("66105036543749403346524547")
                        .amount(new BigDecimal("200.90"))
                        .build(),
                Transaction.builder()
                        .debitAccount("66105036543749403346524547")
                        .creditAccount("32309111922661937852684864")
                        .amount(new BigDecimal("50.10"))
                        .build()
        );
    }
}