package edu.lysak.generics.reflection;

import java.lang.reflect.TypeVariable;
import java.util.Map;

class GenericDataHolder<K extends String, V extends Number> {
    public Map<K, V> data;

    public void setData(Map<K, V> data) {
        this.data = data;
    }

    public Map<K, V> getData() {
        return data;
    }

    public static void main(String[] args) {
        TypeVariable<Class<GenericDataHolder>>[] typeVariables = GenericDataHolder.class.getTypeParameters();
        System.out.println("Type variables count " + typeVariables.length);

        System.out.println(typeVariables[0]); // K
        System.out.println("First type var upper bound " + typeVariables[0].getBounds()[0]); // java.lang.String

        System.out.println(typeVariables[1]); // V
        System.out.println("Second type var upper  bound " + typeVariables[1].getBounds()[0]); // java.lang.Number
    }
}
