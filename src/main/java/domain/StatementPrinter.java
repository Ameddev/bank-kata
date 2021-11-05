package domain;

import domain.account.Transaction;

import java.util.List;

public class StatementPrinter {
    private static final String HEADER = "| operation | date | amount | balance |";

    public static void print(List<Transaction> history) {
        System.out.println(HEADER);
        history.forEach(StatementPrinter::printLine);
    }

    private static void printLine(Transaction transaction) {
        String line = "| " + transaction.getOperation() +
                " | " + transaction.getDate() +
                " | " + transaction.getAmount().getValue() +
                " | " + transaction.getBalance().getValue() + " |";
        System.out.println(line);
    }
}
