package edu.lysak.patterns.strategy.selection;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int count = Integer.parseInt(scanner.nextLine());
        Person[] persons = new Person[count];

        for (int i = 0; i < count; i++) {
            persons[i] = new Person(scanner.nextLine());
        }

        String[] configs = scanner.nextLine().split("\\s+");

        PersonSelectionAlgorithm alg = create(configs[0], Integer.parseInt(configs[1]));
        SelectionContext ctx = new SelectionContext();
        ctx.setAlgorithm(alg);

        Person[] selected = ctx.selectPersons(persons);
        for (Person p : selected) {
            System.out.println(p.getName());
        }
    }

    private static PersonSelectionAlgorithm create(String algType, int param) {
        return switch (algType) {
            case "STEP" -> new TakePersonsWithStepAlgorithm(param);
            case "LAST" -> new TakeLastPersonsAlgorithm(param);
            default -> throw new IllegalArgumentException("Unknown algorithm type " + algType);
        };
    }
}
