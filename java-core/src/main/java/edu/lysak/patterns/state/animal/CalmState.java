package edu.lysak.patterns.state.animal;

public class CalmState implements State {
    private final Animal animal;

    CalmState(Animal animal) {
        this.animal = animal;
    }

    @Override
    public void observe() {
        System.out.println(animal + " is lying in the grass.");
    }

    @Override
    public void onStateEntry() {
        System.out.println(animal + " calms down and stops.");
    }

    @Override
    public void onTimePassed() {
        animal.setState(new AngryState(animal));
    }
}
