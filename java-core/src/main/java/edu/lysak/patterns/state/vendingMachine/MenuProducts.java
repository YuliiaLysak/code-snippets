package edu.lysak.patterns.state.vendingMachine;

public class MenuProducts implements State {
    private final VendingMachine machine;
    private int cursor = 0;

    MenuProducts(VendingMachine machine) {
        this.machine = machine;
        System.out.println("Select item:");
        System.out.println(machine.getProducts().get(cursor));
    }

    @Override
    public void onLeftPressed() {
        machine.setState(new MenuQuantity(machine, machine.getProducts().get(cursor)));
    }

    @Override
    public void onRightPressed() {
        cursor = ++cursor % machine.getProducts().size();
        System.out.println(machine.getProducts().get(cursor));
    }
}
