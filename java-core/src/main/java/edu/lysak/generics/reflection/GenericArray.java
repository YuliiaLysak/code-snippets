package edu.lysak.generics.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;

public class GenericArray<T> {
    public T[] genericArrayField;

    public static void main(String[] args) throws NoSuchFieldException {
        Field field = GenericArray.class.getDeclaredField("genericArrayField");
        GenericArrayType arrayType = (GenericArrayType) field.getGenericType();
        System.out.println(arrayType); // T[]
        System.out.println(arrayType.getGenericComponentType()); // T
    }
}
