package pl.greencode.application.transactions.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.greencode.application.transactions.pojo.request.Transaction;
import pl.greencode.application.transactions.pojo.response.Account;
import pl.greencode.application.transactions.service.TransactionsService;

import java.util.List;

@RestController
@AllArgsConstructor
public class TransactionsController {
    private final TransactionsService transactionsService;

    @PostMapping("/transactions/report")
    public List<Account> reportTransactions(@Valid @RequestBody List<Transaction> transactions) {
        return transactionsService.createReport(transactions);
    }
}
