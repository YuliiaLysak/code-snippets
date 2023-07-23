package edu.lysak.java8Training;

public class LambdasLocalVarTest {
    public static void main(String[] args) {
        // foo - free variables
        Foo foo = new Foo();
        foo.s = "Foo";
        foo.s = "Bar";
        Runnable r = () -> {
            foo.s = "FooBar";
            System.out.println(foo); // capturing lambda
        };

        // bar - free variable
        String bar = "Bar";
        // compile time error:
        // Local variable bar defined in an enclosing scope must be final or effectively final
        r = () -> System.out.println(bar);
//		bar = "Foo";
    }
}

class Foo {
    String s;
}
