package org.example.algorithms;

import org.example.metrics.Metrics;
import org.example.utils.ArrayUtils;

public class MergeSort {

    private static int[] aux;
    private static final int CUTOFF = 7;

    public static void sort(int[] array) {

        if (array == null || array.length == 0) {
            return;
        }
        aux = new int[array.length];

        Metrics.startTimer();
        sort(array, 0, array.length - 1);

        double timeMs = Metrics.stopTimeAndGet();
        Metrics.writeToCSV("MergeSort", array.length, timeMs);
    }

    private static void sort(int[] array, int low, int high) {

        Metrics.enterRecursion();

        try {
            if (high <= low) {
                return;
            }

            if (high - low + 1 <= CUTOFF) {
                ArrayUtils.insertionSort(array, low, high);
                return;
            }
            int mid = low + (high - low) / 2;

            sort(array, low, mid);
            sort(array, mid + 1, high);

            merge(array, low, mid, high);

        } finally {
            Metrics.exitRecursion();
        }
    }


    private static void merge(int[] array, int low, int mid, int high) {
        int i = low;
        int j = mid + 1;

        for (int k = low; k <= high; k++) {
            aux[k] = array[k];
        }

        for (int k = low; k <= high; k++) {
            Metrics.incrementComparisons();
            if (i > mid) {
                array[k] = aux[j++];
            } else if (j > high) {
                array[k] = aux[i++];
            } else if (aux[j] < aux[i]) {
                array[k] = aux[j++];
            } else {
                array[k] = aux[i++];
            }
        }
    }
}