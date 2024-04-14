package edu.lysak.patterns.strategy.findMinMax;

public class MinFindingStrategy implements FindingStrategy {

    public int getResult(int[] numbers) {
        if (numbers.length == 0) {
            return Integer.MAX_VALUE;
        }

        int min = numbers[0];
        for (int i : numbers) {
            if (i < min) {
                min = i;
            }
        }
        return min;
    }
}
