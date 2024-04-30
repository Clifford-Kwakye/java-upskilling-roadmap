package org.example.labOne.strategies;

public class BubbleSort implements SortingStrategy{
    @Override
    public void sort(int[] arr) {
        int arrLength = arr.length;
        boolean swapped;

        for (int i = 0; i < arrLength - 1; i++) {
            swapped = false;

            for (int j = 0; j < arrLength - i - 1; j++) {
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }
    }
}
