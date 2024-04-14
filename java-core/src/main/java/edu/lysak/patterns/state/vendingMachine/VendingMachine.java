package edu.lysak.patterns.state.vendingMachine;

import java.util.List;

public class VendingMachine {
    private final List<String> products = List.of("Juice", "Soda", "Cold Tea");
    private State state;

    VendingMachine() {
        System.out.println("Vending machine is on");
        state = new MenuProducts(this);
    }

    public void pressLeft() {
        state.onLeftPressed();
    }

    public void pressRight() {
        state.onRightPressed();
    }

    public void setState(State state) {
        this.state = state;
    }

    public List<String> getProducts() {
        return products;
    }
}
