package pl.greencode.application.transactions.pojo.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

@Data
@Builder
public class Transaction {
    @Length(min = 26, max = 26)
    private final String debitAccount;
    @Length(min = 26, max = 26)
    private final String creditAccount;
    @NotNull
    private BigDecimal amount;
}
