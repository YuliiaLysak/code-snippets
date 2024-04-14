package edu.lysak.patterns.strategy.selection;

import java.util.ArrayList;
import java.util.List;

class TakePersonsWithStepAlgorithm implements PersonSelectionAlgorithm {
    private final int step;

    public TakePersonsWithStepAlgorithm(int step) {
        this.step = step;
    }

    @Override
    public Person[] select(Person[] persons) {
        if (step == 1) {
            return persons;
        }
        List<Person> selectedPersons = new ArrayList<>();
        for (int i = 0; i < persons.length; i++) {
            if (i % step == 0) {
                selectedPersons.add(persons[i]);
            }
        }
        return selectedPersons.toArray(new Person[selectedPersons.size()]);
    }
}


