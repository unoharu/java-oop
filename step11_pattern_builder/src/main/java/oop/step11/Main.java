package oop.step11;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Builder パターン ===");

        System.out.println("最小構成:");
        Computer minimal = new Computer.Builder("Core i5", 16).build();
        System.out.println(minimal);

        System.out.println("\nフル構成:");
        Computer full = new Computer.Builder("Core i9", 64)
            .storage(2000)
            .gpu("RTX 4090")
            .os("Windows 11")
            .build();
        System.out.println(full);

        System.out.println("\n=== イミュータブルの確認 ===");
        System.out.println("// full.setCpu(\"...\") → コンパイルエラー（setter なし）");
    }
}
