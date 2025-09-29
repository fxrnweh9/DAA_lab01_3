package org.example.algorithms;

import org.example.utils.ArrayUtils;
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

        Metrics.reset();
    }

    private static void sort(int[] array, int low, int high) {

        Metrics.enterRecursion();

        try {
            while (low < high) {

                int randomIndex = low + RANDOM.nextInt(high - low + 1);
                int pivotIndex = ArrayUtils.partition(array, low, high, randomIndex);

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
}