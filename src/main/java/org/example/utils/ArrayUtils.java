package org.example.utils;

import org.example.metrics.Metrics;
import java.util.Random;

public class ArrayUtils {

    private static final Random RANDOM = new Random();

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static int partition(int[] array, int low, int high, int pivotIndex) {
        swap(array, low, pivotIndex);
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

    public static void insertionSort(int[] array, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            for (int j = i; j > low; j--) {
                Metrics.incrementComparisons();

                if (array[j] < array[j - 1]) {
                    swap(array, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }
}