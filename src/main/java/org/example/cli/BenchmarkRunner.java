package org.example.cli;

import org.example.algorithms.BoyerMooreMajorityVoteAlgorithm;
import org.example.metrics.Metrics;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class BenchmarkRunner {

    private static final int[] SIZES = {100, 1000, 10000, 100000};

    public static void main(String[] args) {
        String outputFile = "benchmark-results.csv";

        try (FileWriter writer = new FileWriter(outputFile)) {
            writer.write("n,version,time(ns),comparisons,candidateUpdates\n");

            for (int n : SIZES) {
                int[] array = generateRandomArray(n);

                Metrics metricsBaseline = new Metrics();
                long baselineTime = runBaseline(array, metricsBaseline);

                writer.write(String.format("%d,baseline,%d,%d,%d\n",
                        n, baselineTime, metricsBaseline.comparisons, metricsBaseline.candidateUpdates));

                Metrics metricsOptimized = new Metrics();
                long optimizedTime = runOptimized(array, metricsOptimized);

                writer.write(String.format("%d,optimized,%d,%d,%d\n",
                        n, optimizedTime, metricsOptimized.comparisons, metricsOptimized.candidateUpdates));

                System.out.println("Completed n=" + n);
            }

            System.out.println("Benchmark results saved to " + outputFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static long runBaseline(int[] array, Metrics metrics) {
        long start = System.nanoTime();
        BoyerMooreMajorityVoteAlgorithm.findMajority(array, metrics);
        return System.nanoTime() - start;
    }

    private static long runOptimized(int[] array, Metrics metrics) {
        long start = System.nanoTime();
        BoyerMooreMajorityVoteAlgorithm.findMajorityOptimized(array, metrics);
        return System.nanoTime() - start;
    }

    private static int[] generateRandomArray(int n) {
        Random rand = new Random();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = rand.nextInt(10);
        }
        return arr;
    }
}
