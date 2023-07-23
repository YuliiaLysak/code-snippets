package edu.lysak.generics.reflection;

import java.lang.reflect.Type;
import java.util.Arrays;

// Class that implements generic interface with some type argument
class SomeClass2 implements GenericInterface<Boolean> {
    public static void main(String[] args) {

        Type[] genericInterfaces = SomeClass2.class.getGenericInterfaces(); // GenericInterface<java.lang.Boolean>
        System.out.println(Arrays.toString(genericInterfaces));
    }
}
