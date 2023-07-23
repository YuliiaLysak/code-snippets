package edu.lysak.hashingAlgorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RabinKarpAlgorithm {
    public static void main(String[] args) {
//        String pattern = "ACDC";
//        String text = "BACDCCBA";
//        System.out.println(Hash.polynomialHash(new int[]{1, 3, 4, 3}, 3, 11)); //ACDC
//        int ccbaHash = Hash.polynomialHash(new int[]{3, 3, 2, 1}, 3, 11); //CCBA
//        int dccbHash = Hash.polynomialRollingHash(ccbaHash, 4, 1, 3, 11, pattern.length()); //DCCB
//        int cdccHash = Hash.polynomialRollingHash(dccbHash, 3, 2, 3, 11, pattern.length()); //DCCB
//        System.out.println(cdccHash);
//
//        System.out.println("======= AACCDB ========");
//        System.out.println();
//
//        System.out.println("--------- Polynomial hash ----------------");
//        String pattern1 = "ACC";
//        int patternHash = Hash.polynomialHash(new int[]{1, 3, 3}, 3, 11);
//        System.out.printf("pattern polynomial h(%s) = %d%n", pattern1, patternHash);
//        System.out.printf("polynomial h(%s) = %d%n", "CDB", Hash.polynomialHash(new int[]{3, 4, 2}, 3, 11));
//        System.out.printf("polynomial h(%s) = %d%n", "CCD", Hash.polynomialHash(new int[]{3, 3, 4}, 3, 11));
//        System.out.printf("polynomial h(%s) = %d%n", "ACC", Hash.polynomialHash(new int[]{1, 3, 3}, 3, 11));
//        System.out.printf("polynomial h(%s) = %d%n", "AAC", Hash.polynomialHash(new int[]{1, 1, 3}, 3, 11));
//        System.out.println();
//
//        System.out.println("--------- Rolling hash ----------------");
//
//        int cdbHash = Hash.polynomialHash(new int[]{3, 4, 2}, 3, 11);
//        int ccdHash = Hash.polynomialRollingHash(cdbHash, 3, 2, 3, 11, pattern1.length());
//        int accHash = Hash.polynomialRollingHash(ccdHash, 1, 4, 3, 11, pattern1.length());
//        int aacHash = Hash.polynomialRollingHash(accHash, 1, 3, 3, 11, pattern1.length());
//        System.out.printf("polynomial h(%s) = %d%n", "CDB", cdbHash);
//        System.out.printf("rolling h(%s) = %d%n", "CCD", ccdHash);
//        System.out.printf("rolling h(%s) = %d%n", "ACC", accHash);
//        System.out.printf("rolling h(%s) = %d%n", "AAC", aacHash);
//        System.out.println();
//
//        System.out.println("======= text: DDABCD  pattern: DDA ========");
//        System.out.println();
//
//        System.out.println("--------- Polynomial hash ----------------");
//        String pattern2 = "DDA";
//        int patternHash2 = Hash.polynomialHash(new int[]{4, 4, 1}, 3, 11);
//        System.out.printf("pattern polynomial h(%s) = %d%n", pattern2, patternHash2);
//        System.out.printf("polynomial h(%s) = %d%n", "BCD", Hash.polynomialHash(new int[]{2, 3, 4}, 3, 11));
//        System.out.printf("polynomial h(%s) = %d%n", "ABC", Hash.polynomialHash(new int[]{1, 2, 3}, 3, 11));
//        System.out.printf("polynomial h(%s) = %d%n", "DAB", Hash.polynomialHash(new int[]{4, 1, 2}, 3, 11));
//        System.out.printf("polynomial h(%s) = %d%n", "DDA", Hash.polynomialHash(new int[]{4, 4, 1}, 3, 11));
//        System.out.println();
//
//        System.out.println("--------- Rolling hash ----------------");
//
//        int bcdHash = Hash.polynomialHash(new int[]{2, 3, 4}, 3, 11);
//        int abcHash = Hash.polynomialRollingHash(bcdHash, 1, 4, 3, 11, pattern2.length());
//        int dabHash = Hash.polynomialRollingHash(abcHash, 4, 3, 3, 11, pattern2.length());
//        int ddaHash = Hash.polynomialRollingHash(dabHash, 4, 2, 3, 11, pattern2.length());
//        System.out.printf("polynomial h(%s) = %d%n", "BCD", bcdHash);
//        System.out.printf("rolling h(%s) = %d%n", "ABC", abcHash);
//        System.out.printf("rolling h(%s) = %d%n", "DAB", dabHash);
//        System.out.printf("rolling h(%s) = %d%n", "DDA", ddaHash);
//        System.out.println();

        System.out.println(RabinKarp("ADDABCD", "DDA"));

        List<Integer> occurrences = RabinKarp("ABACABAD", "ABA");
        System.out.println(occurrences); // [0, 4]

        List<Integer> occurrences2 = RabinKarp("AAAA", "AA");
        System.out.println(occurrences2); // [0, 1, 2]
    }

    /* 1 */
    public static long charToLong(char ch) {
        return (long) (ch - 'A' + 1);
    }

    public static List<Integer> RabinKarp(String text, String pattern) {
        /* 2 */
        int a = 53;
        long m = 1_000_000_000 + 9;

        /* 3 */
        long patternHash = 0;
        long currSubstrHash = 0;
        long pow = 1;

        for (int i = 0; i < pattern.length(); i++) {
            patternHash += charToLong(pattern.charAt(i)) * pow;
            patternHash %= m;

            currSubstrHash += charToLong(text.charAt(text.length() - pattern.length() + i)) * pow;
            currSubstrHash %= m;

            if (i != pattern.length() - 1) {
                pow = pow * a % m;
            }
        }

        /* 4 */
        ArrayList<Integer> occurrences = new ArrayList<>();

        for (int i = text.length(); i >= pattern.length(); i--) {
            if (patternHash == currSubstrHash) {
                boolean patternIsFound = true;

                for (int j = 0; j < pattern.length(); j++) {
                    if (text.charAt(i - pattern.length() + j) != pattern.charAt(j)) {
                        patternIsFound = false;
                        break;
                    }
                }

                if (patternIsFound) {
                    occurrences.add(i - pattern.length());
                }
            }

            if (i > pattern.length()) {
                /* 5 */
                currSubstrHash = (currSubstrHash - charToLong(text.charAt(i - 1)) * pow % m + m) * a % m;
                currSubstrHash = (currSubstrHash + charToLong(text.charAt(i - pattern.length() - 1))) % m;
            }
        }

        Collections.reverse(occurrences);
        return occurrences;
    }
}
