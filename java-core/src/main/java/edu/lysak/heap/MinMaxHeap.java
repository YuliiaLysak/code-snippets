package edu.lysak.heap;

public class MinMaxHeap {
    public static void main(String[] args) {

        // Java program to check whether a given array
        // represents a max-heap or not
//        int[] array = {2, 2, 3, 8, 18, 22, 12, 11, 10, 37, 43, 44, 42, 25, 28, 46, 12, 41, 37, 45};
//        int[] array = {49, 48, 47, 30, 27, 31, 42, 16, 17, 22, 19, 25, 14, 34, 23, 5, 2, 12, 12, 3};
//        int[] array = {46, 40, 44, 37, 35, 41, 42, 29, 37, 5, 6, 3, 37, 27, 15, 4, 1, 8, 18, 3};
//        int[] array = {0, 8, 23, 17, 9, 35, 29, 22, 22, 10, 13, 37, 48, 38, 41, 32, 29, 34, 33, 36};
        int[] array = {27, 34, 23, 4, 30, 21, 30, 43, 16, 0, 36, 33, 8, 12, 43, 8, 31, 27, 32, 6};
        int l = array.length - 1;
        if (isMaxHeap(array, 0, l)) {
            System.out.println("max-heap");
        } else if(isMinHeap(array, 0, l)) {
            System.out.println("min-heap");
        } else {
            System.out.println("not-heap");
        }
    }



    static boolean isMaxHeap(int[] arr, int i, int n) {
        // If a leaf node
        if (i >= (n - 2) / 2) {
            return true;
        }

        // If an internal node and
        // is greater than its
        // children, and same is
        // recursively true for the
        // children
        return arr[i] >= arr[2 * i + 1]
                && arr[i] >= arr[2 * i + 2]
                && isMaxHeap(arr, 2 * i + 1, n)
                && isMaxHeap(arr, 2 * i + 2, n);
    }

    static boolean isMinHeap(int[] arr, int i, int n) {
        // If a leaf node
        if (i >= (n - 2) / 2) {
            return true;
        }

        // If an internal node and
        // is greater than its
        // children, and same is
        // recursively true for the
        // children
        return arr[i] <= arr[2 * i + 1]
                && arr[i] <= arr[2 * i + 2]
                && isMinHeap(arr, 2 * i + 1, n)
                && isMinHeap(arr, 2 * i + 2, n);
    }
}
