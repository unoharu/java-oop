package oop.step10;

import java.util.List;

public class GenericUtils {
    // Bounded type parameter: T must implement Comparable
    static <T extends Comparable<T>> T max(T a, T b) {
        return a.compareTo(b) >= 0 ? a : b;
    }

    // Wildcard: accepts any List regardless of element type
    static void printAll(List<?> list) {
        for (Object item : list) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    // Upper-bounded wildcard: accepts List<Number> or any subtype
    static double sum(List<? extends Number> list) {
        double total = 0;
        for (Number n : list) {
            total += n.doubleValue();
        }
        return total;
    }
}
