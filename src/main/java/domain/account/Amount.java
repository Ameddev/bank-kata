package domain.account;


import domain.exceptions.NegativeAmountException;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;
import java.util.Objects;

public class Amount {
    private final BigDecimal value;
    private final Currency currency;

    public Amount(BigDecimal value) {
        if (value.compareTo(BigDecimal.ZERO) < 0)
            throw new NegativeAmountException();
        this.value = value;
        this.currency = Currency.getInstance(Locale.FRANCE);
    }

    public BigDecimal getValue() {
        return value;
    }

    public Amount add(Amount amount) {
        return new Amount(value.add(amount.getValue()));
    }

    public Amount subtract(Amount amount) {
        return new Amount(value.subtract(amount.getValue()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amount amount = (Amount) o;
        return Objects.equals(value, amount.value) &&
                Objects.equals(currency, amount.currency);
    }
}
