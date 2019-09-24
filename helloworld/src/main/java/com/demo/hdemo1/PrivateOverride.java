package com.demo.hdemo1;

public class PrivateOverride {
    /*private*/ void f() {
        System.out.println("private f()");
        // 然而 private 方法也是 final 的，对于派生类来说是隐蔽的
        // 只有非 private 方法才能被覆写
    }

    public static void main(String[] args) {
        PrivateOverride po = new Derived();
        po.f();
        // private f()
    }
}
