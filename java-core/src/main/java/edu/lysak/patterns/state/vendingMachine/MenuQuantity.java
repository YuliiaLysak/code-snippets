package edu.lysak.patterns.state.vendingMachine;

public class MenuQuantity implements State {
    private final VendingMachine machine;
    private final String product;
    private final int maxQuantity = 4;
    private int quantity = 1;

    MenuQuantity(VendingMachine machine, String product) {
        this.machine = machine;
        this.product = product;
        System.out.println("How many " + this.product + " do you want?");
        System.out.println(quantity);
    }

    @Override
    public void onLeftPressed() {
        if (quantity == 0) {
            System.out.println("Cancelled");
            machine.setState(new MenuProducts(machine));
        } else {
            System.out.printf("Dispensing %s packages of %s%n", quantity, product);
            machine.setState(new MenuProducts(machine));
        }
    }

    @Override
    public void onRightPressed() {
        if (quantity == maxQuantity) {
            quantity = 0;
        } else {
            quantity++;
        }
        System.out.println(quantity);

    }
}
