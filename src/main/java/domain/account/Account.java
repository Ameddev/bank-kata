package domain.account;



import domain.StatementPrinter;
import domain.client.Client;

import java.time.LocalDate;
import java.util.List;

public class Account {

    private Client client;
    private History history;

    public Account(Client client) {
        this.client = client;
        this.history = new History();
    }

    public void deposit(Amount amount, LocalDate localDate) {
        history.deposit(amount, localDate);
    }

    public void withdraw(Amount amount, LocalDate localDate) {
        history.withdraw(amount, localDate);
    }

    public Amount getBalance() {
        return history.getBalance();
    }

    public List<Transaction> getTransactions() {
        return history.getTransactions();
    }

    public void printStatement() {
        StatementPrinter.print(history.getTransactions());
    }
}
