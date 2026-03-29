package oop.step09;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== 静的ネストクラス ===");
        Outer.StaticNested nested = new Outer.StaticNested();
        System.out.println(nested.describe());

        System.out.println("\n=== 内部クラス ===");
        Outer outer = new Outer();
        Outer.Inner inner = outer.new Inner();
        System.out.println(inner.describe());

        System.out.println("\n=== ローカルクラス ===");
        System.out.println(outer.createLocalClass("ローカル変数の値"));

        System.out.println("\n=== 匿名クラス ===");
        Greeter greeter = outer.createAnonymousGreeter();
        System.out.println("Anonymous: Greeter の実装 = " + greeter.greet("Java"));
    }
}
