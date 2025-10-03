package org.example.algorithms;

import org.example.metrics.Metrics;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoyerMooreMajorityVoteAlgorithmTest {

    // -------------------
    // Edge case tests
    // -------------------

    @Test
    void testEmptyArray() {
        assertEquals(-1, BoyerMooreMajorityVoteAlgorithm.findMajority(new int[]{}, new Metrics()),
                "Empty array should return -1");
    }

    @Test
    void testSingleElement() {
        assertEquals(5, BoyerMooreMajorityVoteAlgorithm.findMajority(new int[]{5}, new Metrics()),
                "Single element array should return that element");
    }

    @Test
    void testAllSameElements() {
        assertEquals(2, BoyerMooreMajorityVoteAlgorithm.findMajority(new int[]{2, 2, 2, 2, 2}, new Metrics()),
                "All same elements should return that element");
    }

    @Test
    void testTwoEqualElementsNoMajority() {
        assertEquals(-1, BoyerMooreMajorityVoteAlgorithm.findMajority(new int[]{1, 2}, new Metrics()),
                "Two different elements should return -1 (no majority)");
    }

    @Test
    void testNoMajorityCase() {
        assertEquals(-1, BoyerMooreMajorityVoteAlgorithm.findMajority(new int[]{1, 2, 3, 4}, new Metrics()),
                "No element occurs more than n/2 times → return -1");
    }

    @Test
    void testMajorityAtEnd() {
        assertEquals(3, BoyerMooreMajorityVoteAlgorithm.findMajority(new int[]{1, 2, 3, 3, 3, 3}, new Metrics()),
                "Majority element appearing at the end should still be found");
    }

    @Test
    void testMajorityAtStart() {
        assertEquals(1, BoyerMooreMajorityVoteAlgorithm.findMajority(new int[]{1, 1, 1, 2, 3}, new Metrics()),
                "Majority element at the start should be found");
    }

    @Test
    void testNegativeNumbers() {
        assertEquals(-2, BoyerMooreMajorityVoteAlgorithm.findMajority(new int[]{-2, -2, -2, 1, 2}, new Metrics()),
                "Algorithm should handle negative numbers correctly");
    }

    @Test
    void testMixedNumbersNoMajority() {
        assertEquals(-1, BoyerMooreMajorityVoteAlgorithm.findMajority(new int[]{1, -1, 2, -2, 3, -3}, new Metrics()),
                "Mixed numbers without majority should return -1");
    }

    // -------------------
    // Performance tests
    // -------------------

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

    private void runBenchmark(int[] arr, String distribution) {
        Metrics metrics = new Metrics();
        Runtime runtime = Runtime.getRuntime();

        runtime.gc();
        long beforeMem = runtime.totalMemory() - runtime.freeMemory();

        long start = System.nanoTime();
        BoyerMooreMajorityVoteAlgorithm.findMajority(arr, metrics);
        long end = System.nanoTime();

        long afterMem = runtime.totalMemory() - runtime.freeMemory();

        System.out.printf("n=%d | dist=%s | time=%d ns | mem=%d bytes | comparisons=%d | updates=%d%n",
                arr.length, distribution, (end - start), (afterMem - beforeMem),
                metrics.comparisons, metrics.candidateUpdates);

        assertTrue(true);
    }

    @Test
    void testPerformanceRandom() {
        int[] sizes = {100, 1000, 10000, 100000};
        for (int n : sizes) {
            runBenchmark(generateRandomArray(n), "Random");
        }
    }

    @Test
    void testPerformanceSorted() {
        int[] sizes = {100, 1000, 10000, 100000};
        for (int n : sizes) {
            runBenchmark(generateSortedArray(n), "Sorted");
        }
    }

    @Test
    void testPerformanceReverse() {
        int[] sizes = {100, 1000, 10000, 100000};
        for (int n : sizes) {
            runBenchmark(generateReverseArray(n), "Reverse");
        }
    }

    @Test
    void testPerformanceNearlySorted() {
        int[] sizes = {100, 1000, 10000, 100000};
        for (int n : sizes) {
            runBenchmark(generateNearlySortedArray(n), "NearlySorted");
        }
    }
}
