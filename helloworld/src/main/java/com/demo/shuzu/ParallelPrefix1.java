package com.demo.shuzu;

import java.util.Arrays;

public class ParallelPrefix1 {
    public static void main(String[] args) {
        int[] nums = new int[]{1,5,9,};
        System.out.println(Arrays.toString(nums));
        System.out.println(Arrays.stream(nums).reduce(Integer::sum).getAsInt());


        Arrays.parallelPrefix(nums, Integer::sum);
        System.out.println(Arrays.toString(nums));

    }
}
