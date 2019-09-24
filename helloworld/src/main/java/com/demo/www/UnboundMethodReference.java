package com.demo.www;
class X {
    String f() { return "X::f()"; }
}

 class Y {
    static String f() { return "X::f()"; }
}

interface MakeString {
    String make();
}

interface TransformX {
    String transform(X x);
}

// 方法引用，要么是static方法  要么是 接口方法参数类型一致
public class UnboundMethodReference {
    public static void main(String[] args) {
        // MakeString ms = X::f; // 报错
        MakeString ms = Y::f; // 报错
        TransformX sp = X::f;
        X x = new X();
        System.out.println(sp.transform(x)); // [2]
        System.out.println(x.f()); // 同等效果

        System.out.println(ms.make()); // 同等效果
    }
}
