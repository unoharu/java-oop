package oop.step09;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== 正常な入金・出金 ===");
        BankAccount account = new BankAccount(0);
        account.deposit(1000.0);
        try {
            account.withdraw(500.0);
        } catch (InsufficientFundsException e) {
            System.out.println("例外捕捉: " + e.getMessage());
        }

        System.out.println("\n=== Checked 例外: 残高不足 ===");
        try {
            account.withdraw(1000.0);
        } catch (InsufficientFundsException e) {
            System.out.println("例外捕捉: " + e.getMessage());
        }

        System.out.println("\n=== Unchecked 例外: 負の金額 ===");
        try {
            account.deposit(-100.0);
        } catch (NegativeAmountException e) {
            System.out.println("例外捕捉: " + e.getMessage());
        }

        System.out.println("\n=== multi-catch ===");
        try {
            account.withdraw(-50.0);
        } catch (InsufficientFundsException | NegativeAmountException e) {
            System.out.println("InsufficientFundsException または NegativeAmountException を一括処理: " + e.getMessage());
        }

        System.out.println("\n=== try-with-resources ===");
        try (ResourceDemo resource = new ResourceDemo("ResourceDemo")) {
            resource.process();
        }
    }
}
