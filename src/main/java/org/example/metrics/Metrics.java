package org.example.metrics;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class Metrics {

    private static String algorithmName;
    private static long comparisons = 0;
    private static int maxRecursionDepth = 0;
    private static int currentRecursionDepth = 0;
    private static long startTimeNanos = 0;

    public static void incrementComparisons(){
        comparisons++;
    }

    public static void reset(){
        maxRecursionDepth = 0;
        comparisons = 0;
        currentRecursionDepth = 0;
    }

    public static void enterRecursion(){
        currentRecursionDepth++;

        if (currentRecursionDepth > maxRecursionDepth){
            maxRecursionDepth = currentRecursionDepth;
        }
    }

    public static void exitRecursion(){
        currentRecursionDepth--;
    }

    public static int getCurrentRecursionDepth() {
        return currentRecursionDepth;
    }

    public static int getMaxRecursionDepth() {
        return maxRecursionDepth;
    }

    public static long getComparisons() {
        return comparisons;
    }

    public static void startTimer() {
        startTimeNanos = System.nanoTime();
    }

    public static double stopTimeAndGet(){
        long endTime = System.nanoTime();
        return (endTime - startTimeNanos) / 1_000_000.0;
    }


    public static void writeToCSV(String algorithmName, int N, double time){

        final String nameFile = "results.csv";

        long comparisons = getComparisons();
        int maxDepth = getMaxRecursionDepth();


        try (
            FileWriter fileWriter = new FileWriter(nameFile, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
        ) {
            java.io.File file = new java.io.File(nameFile);
            if (file.length() == 0) {
                printWriter.println("Algorithm,Size_N,Time_ms,Comparisons,Max_Depth");
            }

            printWriter.printf("%s,%d,%.4f,%d,%d\n",
                    algorithmName,
                    N,
                    time,
                    comparisons,
                    maxDepth
            );

            reset();

        } catch (IOException e){
            System.err.println("Error writing to CSV file: " + e.getMessage());
        }


    }
}
