package edu.lysak.patterns.strategy.findMinMax;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String[] elements = scanner.nextLine().split("\\s+");
        int[] numbers;

        if (elements[0].equals("EMPTY")) {
            numbers = new int[0];
        } else {
            numbers = new int[elements.length];
            for (int i = 0; i < elements.length; i++) {
                numbers[i] = Integer.parseInt(elements[i]);
            }
        }

        String type = scanner.nextLine();

        Finder finder = null;

        switch (type) {
            case "MIN":
                finder = new Finder(new MinFindingStrategy());
                break;
            case "MAX":
                finder = new Finder(new MaxFindingStrategy());
                break;
            default:
                break;
        }

        if (finder == null) {
            throw new RuntimeException(
                "Unknown strategy type passed. Please, write to the author of the problem.");
        }

        System.out.println(finder.find(numbers));
    }
}
