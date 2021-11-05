package acceptance;


import domain.account.Amount;
import domain.account.Operation;
import domain.account.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionDataTest {
    private final Operation operation;
    private final BigDecimal amount;
    private final String date;
    private final BigDecimal balance;

    public TransactionDataTest(Operation operation, BigDecimal amount, String date, BigDecimal balance) {
        this.operation = operation;
        this.amount = amount;
        this.date = date;
        this.balance = balance;
    }

    public Transaction toTransaction() {
        return new Transaction(this.operation, LocalDate.parse(this.date), new Amount(this.amount), new Amount(this.balance));
    }

}
