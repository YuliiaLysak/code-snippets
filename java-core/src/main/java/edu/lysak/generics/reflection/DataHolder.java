package edu.lysak.generics.reflection;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DataHolder {
    public List<? extends Number> wildcardField;
    public Map<String, Integer> data;

    public void setData(Map<String, Integer> data) {
        this.data = data;
    }

    public Map<String, Integer> getData() {
        return data;
    }


    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException {
        Field field = DataHolder.class.getDeclaredField("data");
        ParameterizedType parameterizedType1 = (ParameterizedType) field.getGenericType();

        Type rawType = parameterizedType1.getRawType(); // interface java.util.Map
        Type[] argumentTypes = parameterizedType1.getActualTypeArguments(); // class java.lang.String, class java.lang.Integer

        // or you can get type name as a String
        String arg1TypeName = argumentTypes[0].getTypeName(); // java.lang.String
        String arg2TypeName = argumentTypes[1].getTypeName(); // java.lang.Integer

        // it is possible to examine the return type of the getData method
        Method method = DataHolder.class.getMethod("getData");
        ParameterizedType parameterizedType2 = (ParameterizedType) method.getGenericReturnType();
//        System.out.println(parameterizedType2.getRawType());
//        System.out.println(Arrays.toString(parameterizedType2.getActualTypeArguments()));

        // or arguments of the setData method.
        Method method1 = DataHolder.class.getMethod("setData", Map.class);
        Type[] parameterTypes = method1.getGenericParameterTypes();
        ParameterizedType parameterizedType3 = (ParameterizedType) parameterTypes[0]; // method has a single parameter


        // we can obtain the ? extends Number parameter, but not its upper or lower bounds:
        Field wildField = DataHolder.class.getDeclaredField("wildcardField");
        ParameterizedType parameterizedType4 = (ParameterizedType) wildField.getGenericType();
        Type type = parameterizedType4.getActualTypeArguments()[0]; // ? extends Number
        System.out.println(type);

        // To examine the bounds of wildcard types, reflection provides the WildcardType interface:
        WildcardType wildcardType = (WildcardType) parameterizedType4.getActualTypeArguments()[0]; // There is a single parameter
        System.out.println(Arrays.toString(wildcardType.getLowerBounds())); // empty
        System.out.println(Arrays.toString(wildcardType.getUpperBounds())); // Number
    }
}


