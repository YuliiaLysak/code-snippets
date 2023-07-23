package edu.lysak.searchAlgorithms;

public class JumpSearch {

    public static void main(String[] args) {
//        List<Integer> list = Arrays.stream(array).boxed().collect(Collectors.toList());
//        Integer[] array2 = list.toArray(new Integer[array.length]);


//      correct answer - 76
//        int[] array = {0, 1, 2, 3, 4, 5, 8, 9, 10, 12, 13, 15, 16, 18, 19, 20, 21, 22, 23, 26, 27, 28, 30, 31, 32, 33, 34, 35, 36, 40, 41, 42, 43, 47, 49};
//        int[] targets = {10, 13, 17, 19, 21, 30, 31, 32, 42, 44};

//        correct answer - 69
//        int[] array = {1, 2, 3, 4, 5, 6, 8, 10, 12, 13, 14, 15, 16, 17, 18, 20, 21, 27, 28, 29, 30, 32, 33, 34, 35, 36, 38, 40, 42, 47, 49};
//        int[] targets = {0, 1, 10, 18, 30, 38, 40, 44, 47, 48};

//        correct answer -
        int[] array = {0, 1, 2, 4, 5, 6, 7, 8, 9, 10, 12, 14, 15, 16, 17, 18, 19, 20, 21, 23, 25, 27, 28, 29, 30, 31, 32, 33, 34, 36, 37, 38, 39, 40, 41, 42, 43, 44, 47, 48};
        int[] targets = {5, 11, 12, 14, 18, 26, 27, 31, 38, 44};

        int totalCount = 0;
        for (int target : targets) {
//            System.out.print(jumpSearchDecreasingArray(array, target) + " ");
            totalCount += pseudoCodeWithCount(array, target);
        }
        System.out.println(totalCount);
    }

    public static int jumpSearchIncreasingArray(int[] array, int target) {
        int currentRight = 0; // right border of the current block
        int prevRight = 0; // right border of the previous block

        /* If array is empty, the element is not found */
        if (array.length == 0) {
            return -1;
        }

        /* Check the first element */
        if (array[currentRight] == target) {
            return 0;
        }

        /* Calculating the jump length over array elements */
        int jumpLength = (int) Math.sqrt(array.length);

        /* Finding a block where the element may be present */
        while (currentRight < array.length - 1) {

            /* Calculating the right border of the following block */
            currentRight = Math.min(array.length - 1, currentRight + jumpLength);

            if (array[currentRight] >= target) {
                break; // Found a block that may contain the target element
            }

            prevRight = currentRight; // update the previous right block border
        }

        /* If the last block is reached and it cannot contain the target value => not found */
        if ((currentRight == array.length - 1) && target > array[currentRight]) {
            return -1;
        }

        /* Doing linear search in the found block */
        return backwardSearch(array, target, prevRight, currentRight);
    }

    public static int jumpSearchDecreasingArray(int[] array, int target) {
        int currentRight = 0; // right border of the current block
        int prevRight = 0; // right border of the previous block

        /* If array is empty, the element is not found */
        if (array.length == 0) {
            return -1;
        }

        /* Check the first element */
        if (array[currentRight] == target) {
            return 0;
        }

        /* Calculating the jump length over array elements */
        int jumpLength = (int) Math.sqrt(array.length);

        /* Finding a block where the element may be present */
        while (currentRight < array.length - 1) {

            /* Calculating the right border of the following block */
            currentRight = Math.min(array.length - 1, currentRight + jumpLength);

            if (array[currentRight] <= target) {
                break; // Found a block that may contain the target element
            }

            prevRight = currentRight; // update the previous right block border
        }

        /* If the last block is reached and it cannot contain the target value => not found */
        if ((currentRight == array.length - 1) && target < array[currentRight]) {
            return -1;
        }

        /* Doing linear search in the found block */
        return backwardSearch(array, target, prevRight, currentRight);
    }

    public static int backwardSearch(int[] array, int target, int leftExcl, int rightIncl) {
        for (int i = rightIncl; i > leftExcl; i--) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static int jumpSearchIncreasingArrayComparisonCount(int[] array, int target) {
        int comparisonCount = 0;
        int currentRight = 0; // right border of the current block
        int prevRight = 0; // right border of the previous block

        /* If array is empty, the element is not found */
        if (array.length == 0) {
            comparisonCount++;
            return comparisonCount;
        }

        /* Check the first element */
        if (array[currentRight] == target) {
            comparisonCount++;
            return comparisonCount;
        }

        /* Calculating the jump length over array elements */
        int jumpLength = (int) Math.sqrt(array.length);

        /* Finding a block where the element may be present */
        while (currentRight < array.length - 1) {
            comparisonCount++;

            /* Calculating the right border of the following block */
            currentRight = Math.min(array.length - 1, currentRight + jumpLength);

            if (array[currentRight] >= target) {
                comparisonCount++;
                break; // Found a block that may contain the target element
            }

            prevRight = currentRight; // update the previous right block border
        }

        /* If the last block is reached and it cannot contain the target value => not found */
        if ((currentRight == array.length - 1) && target > array[currentRight]) {
            comparisonCount++;
            return comparisonCount;
        }

        /* Doing linear search in the found block */
        return backwardSearchWithCount(array, target, prevRight, currentRight, comparisonCount);
    }

    public static int backwardSearchWithCount(int[] array, int target, int leftExcl, int rightIncl, int comparisonCount) {
        for (int i = rightIncl; i > leftExcl; i--) {
            if (array[i] == target) {
                return comparisonCount;
            }
            comparisonCount++;
        }
        return comparisonCount;
    }

    public static int pseudoCode(int[] array, int value) {
        int step = (int) Math.floor(Math.sqrt(array.length));

        int curr = 0;
        int ind;
        while (curr < array.length) {
            if (array[curr] == value) {
                return curr;
            } else if (array[curr] > value) {
                ind = curr - 1;
                while (ind > curr - step && ind >= 1) {
                    if (array[ind] == value) {
                        return ind;
                    }
                    ind -= 1;
                }
                return -1;
            }
            curr += step;
        }
        ind = array.length - 1;
        while (ind > curr - step) {
            if (array[ind] == value) {
                return ind;
            }
            ind -= 1;
        }
        return -1;
    }

    public static int pseudoCodeWithCount(int[] array, int value) {
        int comparisonCount = 0;
        int step = (int) Math.floor(Math.sqrt(array.length));

        int curr = 0;
        int ind;
        while (curr < array.length) {
            if (array[curr] == value) {
                comparisonCount++;
                return comparisonCount;
            } else if (array[curr] > value) {
                comparisonCount++;
                ind = curr - 1;
                while (ind > curr - step && ind >= 1) {
                    if (array[ind] == value) {
                        comparisonCount++;
                        return comparisonCount;
                    }
                    ind -= 1;
                    comparisonCount++;
                }
                return comparisonCount;
            }
            curr += step;
            comparisonCount++;
        }
        ind = array.length - 1;
        while (ind > curr - step) {
            if (array[ind] == value) {
                comparisonCount++;
                return comparisonCount;
            }
            ind -= 1;
            comparisonCount++;
        }
        return comparisonCount;
    }
}
