package pl.greencode.application.transactions.pojo.response;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Getter
@EqualsAndHashCode
@ToString
@Builder
@Slf4j
public class Account implements Comparable<Account> {
    private final String account;
    private int debitCount;
    private int creditCount;
    private BigDecimal balance;

    public void credit(BigDecimal amount) {
        this.balance = this.balance.add(amount);
        this.creditCount++;
    }

    public void debit(BigDecimal amount) {
        this.balance = this.balance.subtract(amount);
        this.debitCount++;
    }

    @Override
    public int compareTo(Account other) {
        return this.account.compareTo(other.account);
    }
}
