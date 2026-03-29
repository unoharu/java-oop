package oop.step10;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Box<T> ===");
        Box<String> strBox = new Box<>("Hello");
        Box<Integer> intBox = new Box<>(42);
        System.out.println(strBox);
        System.out.println(intBox);

        System.out.println("\n=== Pair<A, B> ===");
        Pair<String, Integer> person = new Pair<>("Alice", 30);
        System.out.println("Pair<String, Integer>: " + person);

        System.out.println("\n=== 境界型パラメータ: max() ===");
        System.out.println("max(3, 7) = " + GenericUtils.max(3, 7));
        System.out.println("max(\"apple\", \"mango\") = " + GenericUtils.max("apple", "mango"));

        System.out.println("\n=== ワイルドカード ===");
        List<Integer> ints = List.of(1, 2, 3, 4, 5);
        System.out.println("整数リストの合計: " + (int) GenericUtils.sum(ints));
        List<Number> numbers = List.of(1, 2.5, 3, 4.0, 5);
        System.out.print("数値リストを表示: ");
        GenericUtils.printAll(numbers);

        System.out.println("\n=== 型消去の確認 ===");
        Box<String> box1 = new Box<>("text");
        Box<Integer> box2 = new Box<>(123);
        System.out.println("Box<String> と Box<Integer> は同じクラス: " + (box1.getClass() == box2.getClass()));
    }
}
