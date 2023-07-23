package edu.lysak.generics.reflection.bridge;

import java.lang.reflect.Method;

public class NumberData extends Data<Number> {
    public void set(Number number) {
        System.out.println("NumberData set");
        super.set(number);
    }

    public static void main(String[] args) {
        for (Method method : NumberData.class.getMethods()) {
            if (method.isBridge()) {
                System.out.println(method.getName());
            }
        }
    }
}
