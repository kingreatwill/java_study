package com.demo.shuzu;

import java.util.Arrays;

public class ParallelPrefix2 {
    public static void main(String[] args) {
        String[] strings = new String[]{"A","bc","23"};
        System.out.println(Arrays.toString(strings));
        Arrays.parallelPrefix(strings, (a, b) -> a + b);
        System.out.println(Arrays.toString(strings));
    }
}
