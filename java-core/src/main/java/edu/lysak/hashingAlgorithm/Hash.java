package edu.lysak.hashingAlgorithm;

import java.util.Arrays;

public class Hash {
    public static void main(String[] args) {
        // Calculate a hash for a string s = BBACD
        // using the polynomial hash function with a = 3 and m = 13
        // (A corresponds to 1, B corresponds to 2, ...,Z corresponds to 26)
        String s1 = "BBACD";
        int[] stringNumbers1 = {2, 2, 1, 3, 4};
        int a1 = 3;
        int m1 = 13;
        System.out.printf("Polynomial hash for string %s = %d%n", s1, polynomialHash(stringNumbers1, a1, m1));

        System.out.println("==================================");

        String s = "AACCDB";
        int[] stringNumbers = {1, 1, 3, 3, 4, 2}; // "CCDB" of "AACCDB"
        String suffix = "CCDB";
        int[] suffixNumbers = Arrays.copyOfRange(stringNumbers, 2, stringNumbers.length);
        String nextSuffix = "ACCD";
        int a = 3;
        int m = 13;
        int hash = polynomialHash(suffixNumbers, a, m);
        System.out.printf("Hash for a suffix of length 4(%s) of string s(%s) = %f%n", suffix, s, hash);
        System.out.printf(
                "Polynomial rolling hash of next suffix(%s) = %d%n",
                nextSuffix,
                polynomialRollingHash(
                        hash,
                        stringNumbers[1],
                        stringNumbers[stringNumbers.length - 1],
                        a,
                        m,
                        nextSuffix.length()
                )
        );
    }

    public static int polynomialHash(int[] stringNumbers, int a, int m) {
        double result = 0.0;
        for (int i = 0; i < stringNumbers.length; i++) {
            result += stringNumbers[i] * Math.pow(a, i);
        }
        return (int) result % m;
    }

    public static int polynomialRollingHash(int prevHash, int nextLetter, int prevLetter, int a, int m, int patternLength) {
        return Math.floorMod((int) ((prevHash - prevLetter * Math.pow(a, patternLength - 1)) * a + nextLetter), m);
    }
}
