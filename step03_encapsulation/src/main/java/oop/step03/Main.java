package oop.step03;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== 口座を開設 ===");
        BankAccount account = new BankAccount("ACC-001", "Alice");
        System.out.println("口座番号: " + account.getAccountNumber());
        System.out.println("名義: " + account.getOwner());
        System.out.println("残高: " + account.getBalance() + " 円");

        System.out.println("\n=== 入金・引き出し ===");
        account.deposit(50000);
        System.out.println("50000.0 円入金後: " + account.getBalance() + " 円");
        account.withdraw(20000);
        System.out.println("20000.0 円引き出し後: " + account.getBalance() + " 円");

        System.out.println("\n=== 名義変更 ===");
        account.setOwner("Bob");
        System.out.println("名義変更後: " + account.getOwner());

        System.out.println("\n=== 不正な操作は例外で弾かれる ===");
        try {
            account.deposit(-100);
        } catch (IllegalArgumentException e) {
            System.out.println("マイナス入金: " + e.getMessage());
        }

        try {
            account.withdraw(999999);
        } catch (IllegalStateException e) {
            System.out.println("残高超過引き出し: " + e.getMessage());
        }

        try {
            account.setOwner("  ");
        } catch (IllegalArgumentException e) {
            System.out.println("空の名義: " + e.getMessage());
        }
    }
}
