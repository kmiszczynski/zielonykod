package pl.greencode.application.transactions.service;

import org.springframework.stereotype.Service;
import pl.greencode.application.transactions.pojo.request.Transaction;
import pl.greencode.application.transactions.pojo.response.Account;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class TransactionsService {
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    public List<Account> createReport(List<Transaction> transactions) {
        Map<String, Account> accountsMap = createAccountsMap(transactions);
        CompletableFuture<?>[] futures = transactions.stream()
                .map(transaction -> CompletableFuture.runAsync(() -> processTransaction(transaction, accountsMap), executorService))
                .toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(futures).join();

        return accountsMap.values().stream()
                .sorted()
                .toList();
    }

    private Map<String, Account> createAccountsMap(List<Transaction> transactions) {
        Map<String, Account> accountMap = new HashMap<>();
        transactions.forEach(transaction -> supplyWithAccounts(transaction, accountMap));
        return accountMap;
    }

    private void supplyWithAccounts(Transaction transaction, Map<String, Account> accountMap) {
        accountMap.putIfAbsent(transaction.getDebitAccount(), Account.builder()
                .account(transaction.getDebitAccount())
                .balance(BigDecimal.ZERO)
                .build());
        accountMap.putIfAbsent(transaction.getCreditAccount(), Account.builder()
                .account(transaction.getCreditAccount())
                .balance(BigDecimal.ZERO)
                .build());
    }

    private void processTransaction(Transaction transaction, Map<String, Account> accountsMap) {
        Account debitAccount = accountsMap.get(transaction.getDebitAccount());
        Account creditAccount = accountsMap.get(transaction.getCreditAccount());

        Object lock1 = debitAccount.compareTo(creditAccount) < 0 ? debitAccount : creditAccount;
        Object lock2 = debitAccount.compareTo(creditAccount) < 0 ? creditAccount : debitAccount;
        synchronized (lock1) {
            synchronized (lock2) {
                debitAccount.debit(transaction.getAmount());
                creditAccount.credit(transaction.getAmount());
            }
        }
    }
}
