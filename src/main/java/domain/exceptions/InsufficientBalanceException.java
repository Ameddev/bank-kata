package domain.exceptions;

public class InsufficientBalanceException extends RuntimeException{
    public InsufficientBalanceException() {
        super("Business Error: Account balance is not sufficient.");
    }
}
