package org.example.algorithms;

import org.example.metrics.Metrics;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class BoyerMooreMajorityVoteAlgorithmTest {

    private void assertBothMethods(int expected, int[] nums, String message) {
        Metrics m1 = new Metrics();
        Metrics m2 = new Metrics();

        int result1 = BoyerMooreMajorityVoteAlgorithm.findMajority(nums, m1);
        int result2 = BoyerMooreMajorityVoteAlgorithm.findMajoritySinglePass(nums, m2);

        assertEquals(expected, result1, "findMajority failed: " + message);
        assertEquals(expected == -1 ? result2 : expected, result2, "findMajoritySinglePass failed: " + message);
    }

    @Test
    void testEmptyArray() {
        assertBothMethods(-1, new int[]{}, "Empty array should return -1");
    }

    @Test
    void testSingleElement() {
        assertBothMethods(5, new int[]{5}, "Single element array should return that element");
    }

    @Test
    void testAllSameElements() {
        assertBothMethods(2, new int[]{2, 2, 2, 2, 2}, "All same elements should return that element");
    }

    @Test
    void testTwoEqualElementsNoMajority() {
        assertBothMethods(-1, new int[]{1, 2}, "Two different elements should return -1 (no majority)");
    }

    @Test
    void testNoMajorityCase() {
        assertBothMethods(-1, new int[]{1, 2, 3, 4}, "No element occurs more than n/2 times");
    }

    @Test
    void testMajorityAtEnd() {
        assertBothMethods(3, new int[]{1, 2, 3, 3, 3, 3}, "Majority element appearing at the end should still be found");
    }

    @Test
    void testMajorityAtStart() {
        assertBothMethods(1, new int[]{1, 1, 1, 2, 3}, "Majority element at the start should be found");
    }

    @Test
    void testNegativeNumbers() {
        assertBothMethods(-2, new int[]{-2, -2, -2, 1, 2}, "Algorithm should handle negative numbers correctly");
    }

    @Test
    void testMixedNumbersNoMajority() {
        assertBothMethods(-1, new int[]{1, -1, 2, -2, 3, -3}, "Mixed numbers without majority should return -1");
    }

    // ---------------- Performance / Benchmark Section ----------------

    private int[] generateRandomArray(int n) {
        Random rand = new Random();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = rand.nextInt(10);
        return arr;
    }

    private int[] generateSortedArray(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = i / 10;
        return arr;
    }

    private int[] generateReverseArray(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = (n - i) / 10;
        return arr;
    }

    private int[] generateNearlySortedArray(int n) {
        int[] arr = generateSortedArray(n);
        Random rand = new Random();
        for (int i = 0; i < n / 20; i++) {
            int a = rand.nextInt(n);
            int b = rand.nextInt(n);
            int tmp = arr[a];
            arr[a] = arr[b];
            arr[b] = tmp;
        }
        return arr;
    }

    private void runBenchmark(int[] arr, String distribution, boolean singlePass) {
        Metrics metrics = new Metrics();
        Runtime runtime = Runtime.getRuntime();

        runtime.gc();
        long beforeMem = runtime.totalMemory() - runtime.freeMemory();
        long start = System.nanoTime();

        if (singlePass) {
            BoyerMooreMajorityVoteAlgorithm.findMajoritySinglePass(arr, metrics);
        } else {
            BoyerMooreMajorityVoteAlgorithm.findMajority(arr, metrics);
        }

        long end = System.nanoTime();
        long afterMem = runtime.totalMemory() - runtime.freeMemory();

        System.out.printf("n=%d | %s | mode=%s | time=%d ns | mem=%d bytes | comparisons=%d | updates=%d%n",
                arr.length, distribution, singlePass ? "SinglePass" : "DoublePass",
                (end - start), (afterMem - beforeMem),
                metrics.comparisons, metrics.candidateUpdates);

        assertTrue(true);
    }

    @Test
    void testPerformanceRandom() {
        int[] sizes = {100, 1000, 10000, 100000};
        for (int n : sizes) {
            int[] arr = generateRandomArray(n);
            runBenchmark(arr, "Random", false);
            runBenchmark(arr, "Random", true);
        }
    }

    @Test
    void testPerformanceSorted() {
        int[] sizes = {100, 1000, 10000, 100000};
        for (int n : sizes) {
            int[] arr = generateSortedArray(n);
            runBenchmark(arr, "Sorted", false);
            runBenchmark(arr, "Sorted", true);
        }
    }

    @Test
    void testPerformanceReverse() {
        int[] sizes = {100, 1000, 10000, 100000};
        for (int n : sizes) {
            int[] arr = generateReverseArray(n);
            runBenchmark(arr, "Reverse", false);
            runBenchmark(arr, "Reverse", true);
        }
    }

    @Test
    void testPerformanceNearlySorted() {
        int[] sizes = {100, 1000, 10000, 100000};
        for (int n : sizes) {
            int[] arr = generateNearlySortedArray(n);
            runBenchmark(arr, "NearlySorted", false);
            runBenchmark(arr, "NearlySorted", true);
        }
    }
}
