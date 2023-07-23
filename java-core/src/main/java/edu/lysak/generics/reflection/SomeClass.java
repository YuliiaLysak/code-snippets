package edu.lysak.generics.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

class SomeClass<T> {
    public Map<String, Integer> map;

    public List<? extends Number> getList(T obj) {
        return null;
    }

    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException {
        System.out.println(SomeClass.class.toGenericString()); // class SomeClass<T>

        Field mapField = SomeClass.class.getDeclaredField("map");
        System.out.println(mapField.toGenericString()); // public java.util.Map<java.lang.String, java.lang.Integer> SomeClass.map

        Method method = SomeClass.class.getDeclaredMethod("getList", Object.class);
        System.out.println(method.toGenericString()); // public java.util.List<? extends java.lang.Number> SomeClass.getList(T obj)


    }
}
