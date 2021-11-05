package domain;


import domain.account.Account;
import domain.account.Amount;
import domain.account.Operation;
import domain.account.Transaction;
import domain.client.Client;
import domain.exceptions.NegativeAmountException;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AccountTest {

    private Account account;
    private StatementPrinter statementPrinter;

    @Before
    public void setUp() {
        statementPrinter = mock(StatementPrinter.class);
        final Client client = new Client("202111", "clientName");
        account = new Account(client);
    }

    @Test
    public void should_increase_balance_when_make_deposit() {
        final Amount amount = new Amount(valueOf(200));

        account.deposit(amount, LocalDate.now());

        assertThat(account.getBalance()).isEqualTo(amount);
    }

    @Test
    public void should_decrease_balance_when_make_withdrawal() {
        account.deposit(new Amount(valueOf(300)), LocalDate.now());

        account.withdraw(new Amount(valueOf(200)), LocalDate.now());

        assertThat(account.getBalance()).isEqualTo(new Amount(valueOf(100)));
    }

    @Test
    public void should_render_history_of_all_transactions() {
        final Amount firstTransactionAmount = new Amount(valueOf(500));
        final Amount secondTransactionAmount = new Amount(valueOf(200));
        final List<Transaction> expectedHistory = Arrays.asList(
                new Transaction(Operation.DEPOSIT, LocalDate.now(), firstTransactionAmount, new Amount(valueOf(500))),
                new Transaction(Operation.WITHDRAWAL, LocalDate.now(), secondTransactionAmount, new Amount(valueOf(300)))
        );

        account.deposit(firstTransactionAmount, LocalDate.now());
        account.withdraw(secondTransactionAmount, LocalDate.now());

        assertThat(account.getTransactions()).containsExactlyElementsOf(expectedHistory);
    }

    @Test
    public void should_throw_exception_when_negative_transaction_amount() {
        assertThatThrownBy(() -> new Amount(valueOf(-500)))
                .isInstanceOf(NegativeAmountException.class)
                .hasMessage("Business Error: Negative amounts are not allowed.");
    }


    private Transaction generateSampleTransaction() {
        return new Transaction(Operation.DEPOSIT,
                LocalDate.now(),
                new Amount(valueOf(500)),
                new Amount(valueOf(500)));
    }
}