package org.example.cli;

import org.example.algorithms.BoyerMooreMajorityVoteAlgorithm;
import org.example.metrics.Metrics;

import java.util.Scanner;
import java.util.Random;

public class BenchmarkRunner {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Metrics metrics = new Metrics();

        int n = sc.nextInt();
        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            int input = sc.nextInt();
            array[i] = input == -1 ? new Random().nextInt(10) : input;
        }

        long start = System.nanoTime();
        int majority = BoyerMooreMajorityVoteAlgorithm.findMajority(array, metrics);
        long end = System.nanoTime();

        System.out.println("Array: " + java.util.Arrays.toString(array));
        System.out.println("Majority element: " + majority);
        System.out.println("Execution time (ns): " + (end - start));
        System.out.println(metrics);
    }
}
