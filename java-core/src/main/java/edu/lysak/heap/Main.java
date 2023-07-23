package edu.lysak.heap;

public class Main {
    public static void main(String[] args) {
//        int[] array = {33, 38, 49, 23, 46, 13, 6, 40, 3, 9, 22, 42, 23, 46, 17, 47, 19, 7, 23, 12}; // result = 8;
        int[] array = {10, 38, 42, 36, 28, 33, 15, 13, 30, 20, 27, 15, 3, 44, 32, 43, 20, 32, 17, 6}; // result = ;
        System.out.println(getUnnecessaryElementCountFromMaxHeap(array));
    }

    private static int getUnnecessaryElementCountFromMaxHeap(int[] array) {
        int result = 0;
        int length = array.length;
        for (int i = 0; i < length; i++) {
            int parent = array[i];
            int leftIndex = 2 * i + 1;
            int rightIndex = 2 * i + 2;
//            if (leftIndex < length && rightIndex < length) {
//                if (parent < array[leftIndex] || parent < array[rightIndex]) {
//                    result++;
//                }
//            }

            if (leftIndex < length && array[leftIndex] > parent
            || rightIndex < length && array[rightIndex] > parent) {
                result++;
            }
        }
        return result;
    }

    /*
    Pseudocode:
    count = 0
    for index_now in [1..len(T)]
    index_left = index_now * 2
    index_right = index_now * 2 + 1
    if index_left < len(T) and T[index_left] > T[index_now] \
        or index_right < len(T) and T[index_right] > T[index_now]
        count += 1
     */
}
