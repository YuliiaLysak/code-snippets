package edu.lysak.concurentMaps;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentMap {
    static {
        new ArrayList<>();
    }

    public static void main(String[] args) throws InterruptedException {

        Map<Integer, String> map = new ConcurrentHashMap<>();

        Thread writer1 = new Thread(() -> addPositiveNumbers(map));
        Thread writer2 = new Thread(() -> addNegativeNumbers(map));

        writer1.start();
        writer2.start();

        // Here, in the main thread, we can use Iterator, retrieve values or update the map

        writer1.join(); // wait for writer1 thread
        writer2.join(); // wait for writer2 thread

        System.out.println(map.size()); // the result is always the same
    }

    private static void addPositiveNumbers(Map<Integer, String> target) {
        for (int i = 0; i < 100_000; i++) {
            target.put(i, "Number is " + i);
        }
    }

    private static void addNegativeNumbers(Map<Integer, String> target) {
        for (int i = -100_000; i < 0; i++) {
            target.put(i, "Number is " + i);
        }
    }
}
