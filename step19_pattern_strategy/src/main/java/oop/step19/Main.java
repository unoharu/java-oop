package oop.step19;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] original = {5, 3, 8, 1, 9, 2};

        System.out.println("=== Strategy パターン: ソートアルゴリズム ===");

        System.out.println("BubbleSort を使用:");
        Sorter sorter = new Sorter(new BubbleSort());
        sorter.sort(Arrays.copyOf(original, original.length));

        System.out.println("\nSelectionSort に切り替え:");
        sorter.setStrategy(new SelectionSort());
        sorter.sort(Arrays.copyOf(original, original.length));

        System.out.println("\n=== ラムダ式で Strategy を置き換え ===");
        System.out.println("Arrays.sort をラムダで渡す:");
        sorter.setStrategy(data -> Arrays.sort(data));
        sorter.sort(Arrays.copyOf(original, original.length));
    }
}
