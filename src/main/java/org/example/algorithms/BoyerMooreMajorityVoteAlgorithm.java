package org.example.algorithms;

public class BoyerMooreMajorityVoteAlgorithm {
    public static int findMajority(int[] nums) {
        if (nums==null || nums.length==0) return -1;

        int count = 0;
        int candidate = -1;

        for(int num : nums) {
            if (count == 0) {
                candidate = num;
                count = 1;
            }
            else if (num == candidate) {
                count++;
            }
            else {
                count--;
            }
        }
        count = 0;

        for(int num : nums) {
            if (num == candidate) {
                count++;
            }
        }

        return count > nums.length / 2 ? candidate : -1;
    }
}