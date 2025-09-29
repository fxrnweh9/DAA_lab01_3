package org.example.algorithms;

import org.example.metrics.Metrics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ClosestPair {

    public static class Point {
        double x, y;
        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    private static double dist(Point a, Point b) {
        Metrics.incrementComparisons();
        double dx = a.x - b.x;
        double dy = a.y - b.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static double closestPair(Point[] points) {
        Metrics.reset();
        Metrics.startTimer();

        Arrays.sort(points, Comparator.comparingDouble(p -> p.x));
        double result = closestUtil(points, 0, points.length - 1);

        double time = Metrics.stopTimeAndGet();
        Metrics.writeToCSV("ClosestPair", points.length, time);

        return result;
    }

    private static double closestUtil(Point[] points, int left, int right) {
        Metrics.enterRecursion();

        if (right - left <= 3) {
            double min = Double.POSITIVE_INFINITY;
            for (int i = left; i <= right; i++) {
                for (int j = i + 1; j <= right; j++) {
                    min = Math.min(min, dist(points[i], points[j]));
                }
            }
            Arrays.sort(points, left, right + 1, Comparator.comparingDouble(p -> p.y));
            Metrics.exitRecursion();
            return min;
        }

        int mid = (left + right) / 2;
        double midX = points[mid].x;

        double dl = closestUtil(points, left, mid);
        double dr = closestUtil(points, mid + 1, right);
        double d = Math.min(dl, dr);

        Point[] merged = new Point[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            if (points[i].y < points[j].y) merged[k++] = points[i++];
            else merged[k++] = points[j++];
        }
        while (i <= mid) merged[k++] = points[i++];
        while (j <= right) merged[k++] = points[j++];
        System.arraycopy(merged, 0, points, left, merged.length);

        List<Point> strip = new ArrayList<>();
        for (int idx = left; idx <= right; idx++) {
            if (Math.abs(points[idx].x - midX) < d) {
                strip.add(points[idx]);
            }
        }

        for (int idx = 0; idx < strip.size(); idx++) {
            for (int jdx = idx + 1; jdx < strip.size() &&
                    (strip.get(jdx).y - strip.get(idx).y) < d; jdx++) {
                d = Math.min(d, dist(strip.get(idx), strip.get(jdx)));
            }
        }

        Metrics.exitRecursion();
        return d;
    }
}
