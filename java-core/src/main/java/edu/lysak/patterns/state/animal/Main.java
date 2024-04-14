package edu.lysak.patterns.state.animal;

public class Main {
    public static void main(String[] args) {
        Animal animal = new Animal();
        animal.setState(new CalmState(animal));

        for (int i = 0; i < 4; i++) {
            animal.observe();
            animal.onTimePassed();
        }
    }
}
