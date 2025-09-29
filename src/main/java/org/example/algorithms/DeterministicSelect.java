package org.example.algorithms;

import org.example.metrics.Metrics;
import org.example.utils.ArrayUtils;

import java.util.Arrays;


public class DeterministicSelect {

    public static int select(int[] array, int k) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }
        if (k < 0 || k >= array.length) {
            throw new IllegalArgumentException("k out of bounds");
        }
        return selectHelper(array, 0, array.length - 1, k);
    }

    private static int selectHelper(int[] array, int left, int right, int k) {
        Metrics.enterRecursion();
        try {
            int len = right - left + 1;

            if (len <= 5) {
                Arrays.sort(array, left, right + 1);
                return array[left + k];
            }

            int numGroups = (len + 4) / 5;
            int[] medians = new int[numGroups];
            for (int gi = 0; gi < numGroups; gi++) {
                int subLeft = left + gi * 5;
                int subRight = Math.min(subLeft + 4, right);
                Arrays.sort(array, subLeft, subRight + 1);
                int medianIndex = (subLeft + subRight) / 2;
                medians[gi] = array[medianIndex];
            }

            int pivotValue = select(medians, medians.length / 2);

            int pivotIndex = left;
            boolean found = false;
            for (int i = left; i <= right; i++) {
                Metrics.incrementComparisons();
                if (array[i] == pivotValue) {
                    pivotIndex = i;
                    found = true;
                    break;
                }
            }

            int finalPivotPos = ArrayUtils.partition(array, left, right, pivotIndex);

            int leftSize = finalPivotPos - left;
            if (k == leftSize) {
                return array[finalPivotPos];
            } else if (k < leftSize) {
                return selectHelper(array, left, finalPivotPos - 1, k);
            } else {
                return selectHelper(array, finalPivotPos + 1, right, k - leftSize - 1);
            }
        } finally {
            Metrics.exitRecursion();
        }
    }
}
