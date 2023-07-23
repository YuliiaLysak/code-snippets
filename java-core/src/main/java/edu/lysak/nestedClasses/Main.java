package edu.lysak.nestedClasses;

public class Main {
    public static void main(String[] args) {
        //inner static
        Outer.InnerStatic innerStatic = new Outer.InnerStatic();

        //inner
        Outer out = new Outer();
        Outer.Inner inner = out.new Inner();
    }
}
