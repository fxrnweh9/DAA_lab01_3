import org.example.algorithms.MergeSort;
import org.example.metrics.Metrics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MergeSortTest {


    @BeforeEach
    void setup() {
        Metrics.reset();
    }

    @Test
    void testStandardSort() {
        int[] input = {5, 2, 9, 1, 5, 6};
        int[] expected = {1, 2, 5, 5, 6, 9};

        MergeSort.sort(input);

        assertArrayEquals(expected, input, "The array must be properly sorted.");

        assertEquals(0, Metrics.getComparisons(), "Metrics should be reset (to 0) after writing to CSV.");
    }
    @Test
    void testEmpty() {
        int[] emptyArr = {};

        MergeSort.sort(emptyArr);

        assertArrayEquals(new int[]{}, emptyArr, "An empty array remains empty.");
    }

}