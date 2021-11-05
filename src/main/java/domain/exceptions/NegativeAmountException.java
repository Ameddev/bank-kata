package domain.exceptions;

public class NegativeAmountException extends RuntimeException{
    public NegativeAmountException() {
        super("Business Error: Negative amounts are not allowed.");
    }
}
