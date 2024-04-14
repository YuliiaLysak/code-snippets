package edu.lysak.patterns.state.animal;

public class AngryState implements State {
    private final Animal animal;

    AngryState(Animal animal) {
        this.animal = animal;
    }

    @Override
    public void observe() {
        System.out.println(animal + " is chasing you. Run away!");
    }

    @Override
    public void onStateEntry() {
        System.out.println(animal + " has spotted you.");
    }

    @Override
    public void onTimePassed() {
        animal.setState(new CalmState(animal));
    }
}
