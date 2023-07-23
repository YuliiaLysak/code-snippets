package edu.lysak.searchAlgorithms;

public class LinearSearch {
    public static int count = 0;

    public static void main(String[] args) {
        printTotalComparisonCountOfLinearSearch();
//        int[] array1 = {5, 3, 2, 1, 6, 4, 8, 7, 4, 1, 7, 0, 3, 7, 4, 8, 5, 9, 6, 9, 4, 2, 8, 4, 5, 6, 4, 2, 7, 3, 1, 5, 3, 2, 9, 9, 7, 9, 4, 1, 5, 9, 8, 1, 6, 3, 8, 2, 0, 7, 5, 8, 5, 8, 0, 6, 7, 9, 9, 2, 3, 9, 5, 2, 8, 2, 3, 7, 1, 3, 3, 1, 3, 6, 5, 0, 3, 2, 7, 6, 5, 9, 7, 8, 8, 0, 0, 5, 5, 8, 0, 4, 1, 1, 2, 5, 8, 7, 5, 8};
//        System.out.println("linearSearchCount = " + linearSearchCount(array1, 9));
    }

    private static void printTotalComparisonCountOfLinearSearch() {
        int[] array1 = {2, 0, 4, 8, 9, 1, 3, 8, 0, 9};
        int[] array2 = {8, 7, 4, 3, 8, 8, 4, 8, 0, 7};
        int totalCount = 0;
        for (int i : array1) {
            totalCount += linearSearchComparisonCount(array2, i);
        }
        System.out.println(totalCount);
    }

    public static int linearSearchComparisonCount(int[] numbers, int value) {
        int comparisonCount = 0;
        for (int number : numbers) {
            comparisonCount++;
            if (number == value) {
                break;
            }
        }
        return comparisonCount;
    }

    public static int linearSearchCount(int[] numbers, int value) {
        int count = 0;
        for (int number : numbers) {
            if (number == value) {
                count++;
            }
        }
        return count;
    }

    public static int linearSearchFirstOccurrence(int[] numbers, int value) {
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == value) {
                return i;
            }
        }
        return -1;
    }

    public static int linearSearchLastOccurrence(int[] numbers, int value) {
        for (int i = numbers.length - 1; i >= 0; i--) {
            if (numbers[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
