package oop.step14;

import java.util.Arrays;

public class Sorter {
    private SortStrategy strategy;

    Sorter(SortStrategy strategy) {
        this.strategy = strategy;
    }

    void setStrategy(SortStrategy strategy) {
        this.strategy = strategy;
    }

    void sort(int[] data) {
        System.out.println("入力: " + Arrays.toString(data));
        strategy.sort(data);
        System.out.println("結果: " + Arrays.toString(data));
    }
}
