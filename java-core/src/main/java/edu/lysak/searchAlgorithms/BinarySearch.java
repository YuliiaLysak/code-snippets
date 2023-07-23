package edu.lysak.searchAlgorithms;

public class BinarySearch {
    public static int count = 0;

    public static void main(String[] args) {
        printLoopCountOfBinarySearch();

//        binarySearchNearestNumber();

//        int[] array = {0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 42, 44, 46, 48, 50};
////        int[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8};
//        System.out.println("index = " + binarySearchIterative(array, 6));
//        System.out.println(count);

//        System.out.println("index = " + binarySearchRecursive(array, 6, 0, array.length - 1));
//        System.out.println(count);
    }

    private static void binarySearchNearestNumber() {
        int[] array1 = {2, 9, 10, 20, 28, 47, 55, 55, 57, 64, 69, 77, 86, 87, 99};
        int[] array2 = {75, 69, 72, 24, 24, 11, 50, 82, 51, 94, 5, 86, 69, 71, 51};
        for (int number : array2) {
            System.out.print(binarySearchNearest(array1, number) + " ");
        }
    }

    private static int binarySearchNearest(int[] array, int element) {
        int left = 0;
        int right = array.length - 1;
        if (element > array[right]) {
            return array[right];
        }

        if (element < array[left]) {
            return array[left];
        }

        while (left <= right) {
            int middle = (left + right) / 2;
            if (array[middle] == element) {
                return array[middle];
            } else if (array[middle] < element) {
                left = middle + 1;
//                if (left >= array.length) {
//                    return array[array.length - 1];
//                }
                if (array[left] > element) {
                    if (element - array[middle] <= array[left] - element) {
                        return array[middle];
                    } else {
                        return array[left];
                    }
                }

            } else {
                right = middle - 1;
//                if (right < 0) {
//                    return array[0];
//                }
                if (array[right] < element) {
                    if (element - array[right] <= array[middle] - element) {
                        return array[right];
                    } else {
                        return array[middle];
                    }
                }

            }
        }
        return -1;
    }

    private static void printLoopCountOfBinarySearch() {
        int[] array = {1, 7, 11, 13, 15, 25, 28, 32, 34, 37, 39, 43, 44, 50, 64, 69, 84, 88, 94, 95, 97};
        int totalLoopCount = 0;
        for (int i : array) {
            totalLoopCount += getLoopCountOfBinarySearch(array, i);
        }
        System.out.println("Total loop count = " + totalLoopCount);
    }

    private static int getLoopCountOfBinarySearch(int[] array, int element) {
        int loopCount = 0;

        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            loopCount++;

            int middle = (left + right) / 2;
            if (array[middle] == element) {
                break;
            } else if (array[middle] > element) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return loopCount;

    }

    private static int binarySearchIterative(int[] array, int element) {
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int middle = (left + right) / 2;
            if (array[middle] == element) {
                count++;
                return middle;
            } else if (array[middle] < element) {
                count++;
                left = middle + 1;
            } else {
                count++;
                right = middle - 1;
            }
        }
        return -1;
    }

    public static int binarySearchRecursive(int[] array, int elem, int left, int right) {
        if (left > right) {
            return -1; // search interval is empty, the element is not found
        }

        int mid = left + (right - left) / 2; // the index of the middle element -> or (left + right) >>> 1  or (left + right) / 2

        if (elem == array[mid]) {
            count++;
            return mid; // the element is found, return its index
        } else if (elem < array[mid]) {
            count++;
            return binarySearchRecursive(array, elem, left, mid - 1); // go to the left subarray
        } else {
            count++;
            return binarySearchRecursive(array, elem, mid + 1, right); // go to the right subarray
        }
    }
}
