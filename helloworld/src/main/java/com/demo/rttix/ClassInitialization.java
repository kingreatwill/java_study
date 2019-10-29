package com.demo.rttix;

import java.util.Random;

class Initable {
    static final int STATIC_FINAL = 47;
    static final int STATIC_FINAL2 =  ClassInitialization.rand.nextInt(1000);
    static {
        System.out.println("Initializing Initable");
    }
}

class Initable2 {
    static int staticNonFinal = 147;
    static {
        System.out.println("Initializing Initable2");
    }
}

class Initable3 {
    static int staticNonFinal = 74;
    static {
        System.out.println("Initializing Initable3");
    }
}

public class ClassInitialization {
    public static Random rand = new Random(47);
    public static void
    main(String[] args) throws Exception {
        Class initable = Initable.class; // 仅使用 .class 语法来获得对类对象的引用不会引发初始化
        System.out.println("After creating Initable ref");
        // 不会触发初始化:
        System.out.println(Initable.STATIC_FINAL);
        // 触发初始化:
        System.out.println(Initable.STATIC_FINAL2);
        // 触发初始化:
        System.out.println(Initable2.staticNonFinal);
        Class initable3 = Class.forName("com.demo.rttix.Initable3"); // 使用 Class.forName() 来产生 Class 引用会立即就进行初始化
        System.out.println("After creating Initable3 ref");
        System.out.println(Initable3.staticNonFinal);

    }
}