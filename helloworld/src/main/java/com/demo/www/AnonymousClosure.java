package com.demo.www;

import java.util.List;
import java.util.function.IntSupplier;

public class AnonymousClosure {
    IntSupplier makeFun(int x) {
        int i = 1;
        // 同样规则的应用:
        // i++; // 非等同 final 效果
        // x++; // 同上
        return new IntSupplier() {
            public int getAsInt() { return x + i; }
        };
    }

    public static void main(String[] args) {
        AnonymousClosure c7 = new AnonymousClosure();
        IntSupplier            l1 = c7.makeFun(10);
        System.out.println(l1);

        System.out.println(l1.getAsInt());
        System.out.println(l1.getAsInt());
    }
}
