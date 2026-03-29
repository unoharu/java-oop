package oop.step14;

public class BankAccount {
    private double balance;

    BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    void deposit(double amount) {
        if (amount <= 0) {
            throw new NegativeAmountException(amount);
        }
        balance += amount;
        System.out.println("入金: " + amount + " 円 → 残高: " + balance + " 円");
    }

    void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            throw new NegativeAmountException(amount);
        }
        if (amount > balance) {
            throw new InsufficientFundsException(balance, amount);
        }
        balance -= amount;
        System.out.println("出金: " + amount + " 円 → 残高: " + balance + " 円");
    }

    double getBalance() {
        return balance;
    }
}
