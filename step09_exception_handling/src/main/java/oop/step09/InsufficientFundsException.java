package oop.step09;

// Checked exception: caller must handle or declare
public class InsufficientFundsException extends Exception {
    private final double balance;
    private final double amount;

    InsufficientFundsException(double balance, double amount) {
        super("残高不足です。残高: " + balance + ", 要求: " + amount);
        this.balance = balance;
        this.amount = amount;
    }

    double getBalance() { return balance; }
    double getAmount()  { return amount; }
}
