package oop.step15;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Eager Singleton ===");
        EagerSingleton e1 = EagerSingleton.getInstance();
        EagerSingleton e2 = EagerSingleton.getInstance();
        System.out.println("同じインスタンス: " + (e1 == e2));

        System.out.println("\n=== Lazy Singleton ===");
        LazySingleton l1 = LazySingleton.getInstance();
        LazySingleton l2 = LazySingleton.getInstance();
        System.out.println("同じインスタンス: " + (l1 == l2));

        System.out.println("\n=== Enum Singleton ===");
        EnumSingleton es1 = EnumSingleton.INSTANCE;
        EnumSingleton es2 = EnumSingleton.INSTANCE;
        System.out.println("同じインスタンス: " + (es1 == es2));
        System.out.println("EnumSingleton.INSTANCE.getValue() = " + EnumSingleton.INSTANCE.getValue());

        System.out.println("\n=== Holder Singleton ===");
        HolderSingleton h1 = HolderSingleton.getInstance();
        HolderSingleton h2 = HolderSingleton.getInstance();
        System.out.println("同じインスタンス: " + (h1 == h2));
    }
}
