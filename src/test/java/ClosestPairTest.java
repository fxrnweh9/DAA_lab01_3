import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.example.algorithms.ClosestPair;
import org.example.algorithms.ClosestPair.Point;

public class ClosestPairTest {

    @Test
    public void testSimpleCase() {
        ClosestPair.Point[] points = {
                new Point(0,0),
                new Point(3,4),
                new Point(7,7),
                new Point(1,1)
        };
        double result = ClosestPair.closestPair(points);
        assertEquals(Math.sqrt(2), result, 1e-6);
    }
}
