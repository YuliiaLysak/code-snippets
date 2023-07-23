package edu.lysak.generics;

import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        System.out.println(wildcardsTest(new ArrayList<>()));
    }

    public static Object wildcardsTest(List<? super Object> numbers) {
        numbers.add(10);
        return numbers.get(0);
    }

}
