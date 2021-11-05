

import domain.StatementPrinter;
import domain.account.Account;
import domain.account.Amount;
import domain.client.Client;

import java.math.BigDecimal;
import java.time.LocalDate;

public class StartApp
{
    public static void main(String...args)
    {
        Client client = new Client(String.valueOf(202111), "Ameddev");
        Account account = new Account(client);

        account.deposit(new Amount(BigDecimal.valueOf(600)), LocalDate.of(2020, 11, 10));
        account.deposit(new Amount(BigDecimal.valueOf(800)), LocalDate.of(2020, 12, 15));
        account.withdraw(new Amount(BigDecimal.valueOf(900)), LocalDate.of(2021, 1, 12));
        account.withdraw(new Amount(BigDecimal.valueOf(100)), LocalDate.of(2021, 2, 23));
        account.deposit(new Amount(BigDecimal.valueOf(500)), LocalDate.now());

        account.printStatement();
    }
}
