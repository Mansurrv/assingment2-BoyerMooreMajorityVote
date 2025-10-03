package org.example.algorithms;

import org.example.metrics.Metrics;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoyerMooreMajorityVoteAlgorithmTest {

    @Test
    void testEmptyArray() {
        assertEquals(-1, BoyerMooreMajorityVoteAlgorithm.findMajority(new int[]{}, new Metrics()));
    }

    @Test
    void testSingleElement() {
        assertEquals(1, BoyerMooreMajorityVoteAlgorithm.findMajority(new int[]{1}, new Metrics()));
    }

    @Test
    void testNormalCase() {
        assertEquals(2, BoyerMooreMajorityVoteAlgorithm.findMajority(new int[]{2,2,1,1,2}, new Metrics()));
    }

    @Test
    void testNoMajority() {
        assertEquals(-1, BoyerMooreMajorityVoteAlgorithm.findMajority(new int[]{1,2,3}, new Metrics()));
    }
}
