package edu.lysak.patterns.state.vendingMachine;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VendingMachine machine = new VendingMachine();
        while (scanner.hasNext()) {
            if ("left".equals(scanner.next())) {
                machine.pressLeft();
            } else {
                machine.pressRight();
            }
        }
    }
}
