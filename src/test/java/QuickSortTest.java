import org.example.algorithms.QuickSort;
import org.example.metrics.Metrics;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class QuickSortTest {

    @BeforeEach
    void setup() {
        Metrics.reset();
    }

    @Test
    void testStandardSort() {
        int[] input = {7, 2, 1, 6, 8, 5, 3, 4};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8};

        QuickSort.sort(input);

        assertArrayEquals(expected, input, "Массив должен быть правильно отсортирован.");

        assertEquals(0, Metrics.getComparisons(), "Счетчик сравнений должен быть сброшен.");
    }

    @Test
    void testLargeArrayDepth() {
        int N = 1024;
        int[] input = new int[N];
        for (int i = 0; i < N; i++) {
            input[i] = N - i;
        }

        QuickSort.sort(input);

        assertTrue(Metrics.getMaxRecursionDepth() > 5 && Metrics.getMaxRecursionDepth() < 15,
                "The recursion depth must be O(log N).");

        assertEquals(0, Metrics.getComparisons(), "The comparison counter must be reset.");
        assertEquals(0, Metrics.getMaxRecursionDepth(), "The recursion depth must be reset.");
    }

    @Test
    void testEdgeCases() {
        int[] emptyArr = {};
        int[] singleArr = {42};

        QuickSort.sort(emptyArr);
        assertArrayEquals(new int[]{}, emptyArr, "An empty array remains empty..");

        QuickSort.sort(singleArr);
        assertArrayEquals(new int[]{42}, singleArr, "An array with one element remains unchanged.");

        assertEquals(0, Metrics.getComparisons(), "Metrics need to be reset.");
    }
}