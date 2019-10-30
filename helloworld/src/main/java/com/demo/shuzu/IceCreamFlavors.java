package com.demo.shuzu;

import java.util.Arrays;
import java.util.SplittableRandom;

public class IceCreamFlavors {
    private static SplittableRandom rand =  new SplittableRandom(47);
    static final String[] FLAVORS = {
            "Chocolate", "Strawberry", "Vanilla Fudge Swirl",
            "Mint Chip", "Mocha Almond Fudge", "Rum Raisin",
            "Praline Cream", "Mud Pie"
    };
    public static String[] flavorSet(int n) {
        if(n > FLAVORS.length)
            throw new IllegalArgumentException("Set too big");
        String[] results = new String[n];
        boolean[] picked = new boolean[FLAVORS.length];
        for(int i = 0; i < n; i++) {
            int t;
            do
                t = rand.nextInt(FLAVORS.length);
            while(picked[t]);
            results[i] = FLAVORS[t];
            picked[t] = true;
        }
        return results;
    }
    public static void main(String[] args) {

        int[][] a = {
                { 1, 2, 3, },
                { 4, 5, 6, },
        };
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.deepToString(a));
        int[][][] b = new int[2][2][4];
        System.out.println(Arrays.deepToString(b));
        for(int i = 0; i < 7; i++)
            System.out.println(Arrays.toString(flavorSet(3)));
    }
}
