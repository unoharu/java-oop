package oop.step14;

// Unchecked exception: programming error, no declaration required
public class NegativeAmountException extends RuntimeException {
    NegativeAmountException(double amount) {
        super("金額は正の値でなければなりません: " + amount);
    }
}
