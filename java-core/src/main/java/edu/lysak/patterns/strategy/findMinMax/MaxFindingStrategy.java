package edu.lysak.patterns.strategy.findMinMax;

public class MaxFindingStrategy implements FindingStrategy {

    public int getResult(int[] numbers) {
        if (numbers.length == 0) {
            return Integer.MIN_VALUE;
        }

        int max = numbers[0];
        for (int i : numbers) {
            if (i > max) {
                max = i;
            }
        }
        return max;
    }
}
