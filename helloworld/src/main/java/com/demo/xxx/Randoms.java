package com.demo.xxx;

import java.util.Random;

public class Randoms {
    public static void main(String[] args) {
        new Random(47) //首先，我们给 Random 对象一个种子（以便程序再次运行时产生相同的输出）
                .longs(5, 20)
                .distinct()
                .limit(7)
                .sorted()
                .forEach(System.out::println);
    }
}
