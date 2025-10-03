package org.example.metrics;

public class Metrics {
    public int comparisons = 0;
    public int candidateUpdates = 0;

    public void reset() {
        comparisons = 0;
        candidateUpdates = 0;
    }

    @Override
    public String toString() {
        return "Comparisons: " + comparisons + ", Candidate updates: " + candidateUpdates;
    }
}
