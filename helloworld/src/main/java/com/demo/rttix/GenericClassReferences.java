package com.demo.rttix;

import java.lang.reflect.InvocationTargetException;

public class GenericClassReferences {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class intClass = int.class;
        Class<Integer> genericIntClass = int.class;
        genericIntClass = Integer.class; // 同一个东西
        intClass = double.class;
        // genericIntClass = double.class; // 非法

        Class<?> int2Class = int.class;
        int2Class = double.class;


        // Class<Number> geenericNumberClass = int.class; //出错
        Class<? extends Number> bounded = int.class;
        bounded = double.class;
        bounded = Number.class;

       // Number m = bounded.newInstance();
        // Or anything else derived from Number.
        //genericIntClass.getDeclaredConstructor().newInstance();


    }
}
