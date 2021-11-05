package acceptance;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import domain.account.Account;
import domain.account.Amount;
import domain.account.Transaction;
import domain.client.Client;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class BankClientStepdefs
{
    private Account account;

    @Given("^My account$")
    public void myAccount() {
        Client client = new Client(String.valueOf(202111), "Ameddev");
        account = new Account(client);
    }

    @When("^I deposit (\\d+) euros in this account$")
    public void iDepositEurosInThisAccount(BigDecimal amount) {
        account.deposit(new Amount(amount), LocalDate.now());
    }

    @Then("^The balance in this account is (\\d+) euros$")
    public void theBalanceInThisAccountIsEuros(BigDecimal amount) {
        assertThat(account.getBalance()).isEqualTo(new Amount(amount));
        
    }

    @And("^I withdraw (\\d+) euros from this account$")
    public void iWithdrawEurosFromThisAccount(BigDecimal amount) {
        account.withdraw(new Amount(amount), LocalDate.now());
    }

    @When("^I had deposited (\\d+) euros in this account on (\\d+)-(\\d+)-(\\d+)$")
    public void iHadDepositedEurosInThisAccountOn(BigDecimal amount, int year, int month, int day) {
        account.deposit(new Amount(amount), LocalDate.of(year,month,day));
    }

    @And("^I had withdrawn (\\d+) euros from this account on (\\d+)-(\\d+)-(\\d+)$")
    public void iHadWithdrawnEurosFromThisAccountOn(BigDecimal amount, int year, int month, int day) {
        account.withdraw(new Amount(amount), LocalDate.of(year,month,day));
    }

    @Then("^My history contains (\\d+) transactions$")
    public void myHistoryContainsTransactions(int size) {
        assertThat(account.getTransactions().size()).isEqualTo(size);
    }

    @And("^My history is :$")
    public void myHistoryIs(List<TransactionDataTest> transactions) {
        List<Transaction> expectedTransactions = transactions.stream()
                .map(TransactionDataTest::toTransaction)
                .collect(Collectors.toList());
        assertThat(account.getTransactions()).isEqualTo(expectedTransactions);
    }
}
