
import org.example.algorithms.DeterministicSelect;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class DeterministicSelectTest {

    @Test
    void testSmallArray() {
        int[] input = {7, 2, 9, 1, 5};
        int[] sorted = input.clone();
        Arrays.sort(sorted);

        for (int k = 0; k < input.length; k++) {
            int result = DeterministicSelect.select(input.clone(), k);
            assertEquals(sorted[k], result,
                    "k=" + k + " element should match sorted array");
        }
    }

    @Test
    void testRandomArrays() {
        Random rnd = new Random(42);
        for (int n = 1; n <= 50; n++) {
            int[] input = rnd.ints(n, 0, 100).toArray();
            int[] sorted = input.clone();
            Arrays.sort(sorted);

            for (int k = 0; k < n; k++) {
                int result = DeterministicSelect.select(input.clone(), k);
                assertEquals(sorted[k], result,
                        "Failed on n=" + n + ", k=" + k);
            }
        }
    }

    @Test
    void testSingleElement() {
        int[] input = {42};
        assertEquals(42, DeterministicSelect.select(input, 0),
                "Single element must be returned as is");
    }
}
