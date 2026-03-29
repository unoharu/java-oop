package oop.step19;

public class SelectionSort implements SortStrategy {
    @Override
    public void sort(int[] data) {
        int n = data.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (data[j] < data[minIdx]) {
                    minIdx = j;
                }
            }
            int tmp = data[minIdx];
            data[minIdx] = data[i];
            data[i] = tmp;
        }
    }
}
