package com.demo.shuzu;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.LongStream;

public class CountUpward {
    static long[] fillCounted(int size) {
        return LongStream.iterate(0, i -> i + 1).limit(size).toArray();
    }
    static Random r = new Random(47);
    static int get(int n){
       return r.nextInt(10);
    }

    public static void main(String[] args) {
        long[] l1 = fillCounted(20); // No problem
        System.out.println(Arrays.toString(l1));

        int[] bb =new int[5];
        Arrays.fill(bb, get(0));
        System.out.println(Arrays.toString(bb));
        Arrays.setAll(bb,CountUpward::get);
        System.out.println(Arrays.toString(bb));
        Arrays.parallelSetAll(bb,CountUpward::get);
        System.out.println(Arrays.toString(bb));
        // On my machine, this runs out of heap space:
        // - long[] l2 = fillCounted(10_000_000);
    }
}
