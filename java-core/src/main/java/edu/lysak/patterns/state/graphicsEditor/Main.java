package edu.lysak.patterns.state.graphicsEditor;

import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Canvas canvas = new Canvas();
        switch (scanner.next()) {
            case "selection" -> canvas.setTool(new Selection());
            case "draw" -> {
                canvas.setTool(new Draw(canvas));
                canvas.setColor(scanner.next());
            }
            case "eraser" -> canvas.setTool(new Eraser());
            default -> { }
        }
        canvas.mouseDown();
        canvas.mouseUp();
    }
}