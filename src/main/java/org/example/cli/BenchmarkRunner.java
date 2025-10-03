package org.example.cli;

import org.example.optimizealgorithm.BoyerMooreMajorityVoteAlgorithm;
import org.example.metrics.Metrics;

import java.util.Random;

public class BenchmarkRunner {
    public static void main(String[] args) {
        int n = args.length > 0 ? Integer.parseInt(args[0]) : 1000;
        int[] array = generateRandomArray(n);

        Metrics metrics = new Metrics();
        long start = System.nanoTime();
        int majority = BoyerMooreMajorityVoteAlgorithm.findMajority(array, metrics);
        long end = System.nanoTime();

        System.out.println("Array: " + java.util.Arrays.toString(array));
        System.out.println("Majority element: " + majority);
        System.out.println("Execution time (ns): " + (end - start));
        System.out.println(metrics);
    }

    private static int[] generateRandomArray(int n) {
        Random random = new Random();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(10);
        }
        return arr;
    }
}
