package com.demo.shuzu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class BerylliumSphere {
    private static long counter;
    private final long id = counter++;
    @Override
    public String toString() {
        return "Sphere " + id;
    }
}

public class CollectionComparison {
    public static void main(String[] args) {
        BerylliumSphere[] spheres =
                new BerylliumSphere[10];
        for(int i = 0; i < 5; i++)
            spheres[i] = new BerylliumSphere();
        System.out.println(Arrays.toString(spheres));
        System.out.println(spheres[4]);



        List<Integer> intList = new ArrayList<>(
                Arrays.asList(0, 1, 2, 3, 4, 5));
        intList.add(97);
        System.out.println(intList);
        System.out.println(intList.get(4));
    }
}
