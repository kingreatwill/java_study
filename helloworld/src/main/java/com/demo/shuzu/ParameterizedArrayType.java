package com.demo.shuzu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ClassParameter<T> {
    public T[] f(T[] arg) { return arg; }
}

class MethodParameter {
    public static <T> T[] f(T[] arg) { return arg; }
}

public class ParameterizedArrayType {
    public static void main(String[] args) {
        Integer[] ints = { 1, 2, 3, 4, 5 };
        Double[] doubles = { 1.1, 2.2, 3.3, 4.4, 5.5 };
        Integer[] ints2 =
                new ClassParameter<Integer>().f(ints);
        Double[] doubles2 =
                new ClassParameter<Double>().f(doubles);
        ints2 = MethodParameter.f(ints);
        doubles2 = MethodParameter.f(doubles);

        List<String>[] ls;
        List[] la = new List[10];
        ls = (List<String>[])la; // Unchecked cast
        ls[0] = new ArrayList<>();

        //- ls[1] = new ArrayList<Integer>();
        // error: incompatible types: ArrayList<Integer>
        // cannot be converted to List<String>
        //     ls[1] = new ArrayList<Integer>();
        //             ^

        // The problem: List<String> is a subtype of Object
        Object[] objects = ls; // So assignment is OK
        // Compiles and runs without complaint:
        objects[1] = new ArrayList<>();

        // However, if your needs are straightforward it is
        // possible to create an array of generics, albeit
        // with an "unchecked cast" warning:
        List<BerylliumSphere>[] spheres =
                (List<BerylliumSphere>[])new List[10];
        Arrays.setAll(spheres, n -> new ArrayList<>());
    }
}
