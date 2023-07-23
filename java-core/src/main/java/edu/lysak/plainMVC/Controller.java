package edu.lysak.plainMVC;

import java.util.Scanner;

public class Controller {
    private final Model model;
    private final View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void processInput() {
        Scanner scanner = new Scanner(System.in);
        model.setValue(getInputValue(scanner));
        view.printText(model.getValue());
    }

    private String getInputValue(Scanner scanner) {
        view.printText(View.INPUT_MESSAGE);
        String hello;
        String world;
        while (true) {
            hello = scanner.nextLine();
            if (!View.HELLO.equals(hello)) {
                view.printText(View.WRONG_INPUT);
                continue;
            }
            world = scanner.nextLine();
            if (!View.WORLD.equals(world)) {
                view.printText(View.WRONG_INPUT);
                continue;
            }
            break;
        }
        return hello + " " + world;
    }

}
