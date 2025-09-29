package org.example;

import org.example.metrics.Metrics;
import org.example.algorithms.MergeSort;
import org.example.algorithms.QuickSort;
import org.example.algorithms.ClosestPair;

import java.util.Random;

public class AlgoCLI {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java AlgoCLI <algo> <n>");
            System.out.println("Algos: mergesort | quicksort | closest");
            return;
        }

        String algo = args[0];
        int n = Integer.parseInt(args[1]);

        // данные для теста
        int[] arr = new Random().ints(n, 0, 1000000).toArray();

        Metrics.reset();
        Metrics.startTimer();

        switch (algo.toLowerCase()) {
            case "mergesort":
                MergeSort.sort(arr);
                break;
            case "quicksort":
                QuickSort.sort(arr);
                break;
            case "closest":
                ClosestPair.Point[] points = new ClosestPair.Point[n];
                Random rnd = new Random();
                for (int i = 0; i < n; i++) {
                    points[i] = new ClosestPair.Point(
                            rnd.nextInt(10000),
                            rnd.nextInt(10000)
                    );
                }
                ClosestPair.closestPair(points);
                break;
            default:
                System.out.println("Unknown algorithm: " + algo);
                return;
        }

        double timeMs = Metrics.stopTimeAndGet();

        Metrics.writeToCSV(algo, n, timeMs);

        System.out.println("Done! Metrics written to results.csv");
    }
}
