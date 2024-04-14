package edu.lysak.patterns.state.animal;

public interface State {

    void observe(); // used to observe the current behavior of Animal

    void onStateEntry(); // invoked when the state changes

    void onTimePassed(); // triggers the change of the state
}
