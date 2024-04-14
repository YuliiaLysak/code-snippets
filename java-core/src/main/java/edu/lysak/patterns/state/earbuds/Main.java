package edu.lysak.patterns.state.earbuds;

import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Earbud earbud = new Earbud();
        while (scanner.hasNext()) {
            if ("tap".equals(scanner.next())) {
                earbud.tap();
            } else {
                earbud.doNothing();
            }
        }
    }
}