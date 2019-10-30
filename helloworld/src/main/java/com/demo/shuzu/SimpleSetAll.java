package com.demo.shuzu;

import java.util.Arrays;

class Bob {
    final int id;
    Bob(int n) { id = n; }
    @Override
    public String toString() { return "Bob" + id; }
}

public class SimpleSetAll {
    public static final int SZ = 8;
    static int val = 1;
    static char[] chars = "abcdefghijklmnopqrstuvwxyz"
            .toCharArray();
    static char getChar(int n) { return chars[n]; }
    public static void main(String[] args) {
        int[] ia = new int[SZ];
        long[] la = new long[SZ];
        double[] da = new double[SZ];
        Arrays.setAll(ia, n -> n); // [1]
        Arrays.setAll(la, n -> n);
        Arrays.setAll(da, n -> n);
        System.out.println(Arrays.toString(ia));
        System.out.println(Arrays.toString(la));
        System.out.println(Arrays.toString(da));
        Arrays.setAll(ia, n -> val++); // [2]
        Arrays.setAll(la, n -> val++);
        Arrays.setAll(da, n -> val++);
        System.out.println(Arrays.toString(ia));
        System.out.println(Arrays.toString(la));
        System.out.println(Arrays.toString(da));

        Bob[] ba = new Bob[SZ];
        Arrays.setAll(ba, Bob::new); // [3] 相当于 new Bob(n)
        System.out.println(Arrays.toString(ba));

        Character[] ca = new Character[SZ];
        Arrays.setAll(ca, SimpleSetAll::getChar); // [4]
        System.out.println(Arrays.toString(ca));
    }
}
