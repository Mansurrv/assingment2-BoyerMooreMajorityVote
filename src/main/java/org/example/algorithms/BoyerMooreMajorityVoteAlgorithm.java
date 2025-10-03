package org.example.algorithms;

// The Boyer-Moore voting algorithm is one of the popular algorithms which is used to find the majority element among the given
// elements that have more that N/2 occurences. This works perfectly fine for finding the majority element which takes 2 traversals
// over the given elements, which works in O(n) time complexity and O(1) space complexity.

// This is an example of how to work this algorithm
// Input :{1,1,1,1,2,3,5}
// Output : 1
// Explanation: 1 occurs more than 3 times
//
// Input : {1,2,3}
// Output : -1

import java.util.Scanner;

public class BoyerMooreMajorityVoteAlgorithm {
    public static int findMajority(int[] nums) {
        int count = 0;
        int candidate = -1;

        for(int i=0; i<nums.length; i++) {
            if (count==0) {
                candidate = nums[i];
                count = 1;
            }
            else {
                if (nums[i] == candidate) {
                    count++;
                }
                else {
                    count--;
                }
            }
        }
        count = 0;

        for(int i=0; i<nums.length; i++) {
            if (nums[i] == candidate) {
                count++;
            }
        }
        if (count > (nums.length/2)) {
            return candidate;
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] array = new int[n];
        for(int i=0; i<n; i++) {
            array[i] = sc.nextInt();
        }

        int majority = findMajority(array);
        System.out.println("The majority element is: " +  majority);
    }
}
