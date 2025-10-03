package org.example.optimizealgorithm;

import org.example.metrics.Metrics;

public class BoyerMooreMajorityVoteAlgorithm {

    public static int findMajority(int[] nums, Metrics metrics) {
        if (nums == null || nums.length == 0) return -1;

        int count = 0;
        int candidate = -1;

        for (int num : nums) {
            metrics.comparisons++;
            if (count == 0) {
                candidate = num;
                count = 1;
                metrics.candidateUpdates++;
            } else if (num == candidate) {
                count++;
            } else {
                count--;
            }
        }
        count = 0;

        int majorityThreshold = nums.length / 2;

        for (int i = 0; i < nums.length; i++) {
            metrics.comparisons++;
            if (nums[i] == candidate) {
                count++;
                if (count > majorityThreshold) {
                    return candidate;
                }
            }
            if (count + (nums.length - i - 1) < majorityThreshold) {
                return -1;
            }
        }

        return -1;
    }
}
