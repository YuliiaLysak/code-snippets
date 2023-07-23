package edu.lysak.generics;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<? extends Animal> upperBoundList = new ArrayList<>(List.of(new Cat()));
        Animal animal = upperBoundList.get(0);

        List<? super Animal> lowerBoundList = new ArrayList<>();
        lowerBoundList.add(new Cat());
    }
}
