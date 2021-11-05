package domain.account;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static java.math.BigDecimal.ZERO;

public class History {

    private Amount balance = new Amount(ZERO);
    private List<Transaction> transactions = new ArrayList<>();

    public List<Transaction> getTransactions() {
        return transactions;
    }

    void deposit(Amount amount, LocalDate localDate) {
        balance = balance.add(amount);
        Transaction transaction = new Transaction(Operation.DEPOSIT, localDate, amount, balance);
        transactions.add(transaction);
    }

    void withdraw(Amount amount, LocalDate localDate) {
        balance = balance.subtract(amount);
        Transaction transaction = new Transaction(Operation.WITHDRAWAL, localDate, amount, balance);
        transactions.add(transaction);
    }

    public Amount getBalance() {
        return balance;
    }
}
