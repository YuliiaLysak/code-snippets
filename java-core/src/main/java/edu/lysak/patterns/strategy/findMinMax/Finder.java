package edu.lysak.patterns.strategy.findMinMax;

public class Finder {

    private final FindingStrategy strategy;

    public Finder(FindingStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * It performs the search algorithm according to the given strategy
     */
    public int find(int[] numbers) {
        return strategy.getResult(numbers);
    }
}

