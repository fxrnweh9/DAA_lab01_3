package org.example.algorithms;

import org.example.metrics.Metrics;

import java.util.Random;

public class QuickSort {

    private static final Random RANDOM = new Random();

    public static void sort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }

        Metrics.startTimer();
        sort(array, 0, array.length - 1);

        double timeMs = Metrics.stopTimeAndGet();
        Metrics.writeToCSV("QuickSort", array.length, timeMs);
    }

    private static void sort(int[] array, int low, int high) {

        try {
            while (low < high) {

                Metrics.enterRecursion();

                int pivotIndex = partition(array, low, high);

                if (pivotIndex - low < high - pivotIndex) {
                    sort(array, low, pivotIndex - 1);
                    low = pivotIndex + 1;
                } else {
                    sort(array, pivotIndex + 1, high);
                    high = pivotIndex - 1;
                }
            }
        } finally {
            Metrics.exitRecursion();
        }
    }

    private static int partition(int[] array, int low, int high) {

        int randomIndex = low + RANDOM.nextInt(high - low + 1);
        swap(array, low, randomIndex);

        int pivot = array[low];
        int i = low;

        for (int j = low + 1; j <= high; j++) {
            Metrics.incrementComparisons();

            if (array[j] < pivot) {
                i++;
                swap(array, i, j);
            }
        }

        swap(array, low, i);
        return i;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
