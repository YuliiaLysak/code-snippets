package edu.lysak.garbageCollector;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            list.add(new Object());
        }
        System.out.println();
        System.out.println(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());

//        System.gc();
//        Runtime.getRuntime().gc();

        System.out.println();
        System.out.println(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
    }
}
