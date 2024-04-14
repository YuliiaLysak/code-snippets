package edu.lysak.patterns.strategy.selection;

import java.util.ArrayList;
import java.util.List;

class TakeLastPersonsAlgorithm implements PersonSelectionAlgorithm {
    private final int count;

    public TakeLastPersonsAlgorithm(int count) {
        this.count = count;
    }

    @Override
    public Person[] select(Person[] persons) {
        if (count == 1) {
            return new Person[]{persons[persons.length - 1]};
        }

        List<Person> selectedPersons = new ArrayList<>();
        for (int i = persons.length - count; i < persons.length; i++) {
            selectedPersons.add(persons[i]);
        }
        return selectedPersons.toArray(new Person[selectedPersons.size()]);
    }
}
