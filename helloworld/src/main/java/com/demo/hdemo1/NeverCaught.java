package com.demo.hdemo1;

public class NeverCaught {
    static void f() {
        // 特例：RuntimeException
        // 特例：NullPointerException
        //属于运行时异常的类型有很多，它们会自动被 java 虚拟机抛出，所以不必在异常说明中把它们列出来。
        // 这些异常都是从 RuntimeException 类继承而来，所以既体现了继承的优点，使用起来也很方便。
        // 这构成了一组具有相同特征和行为的异常类型。
        // 并且，也不再需要在异常说明中声明方法将抛出 RuntimeException 类型的异常（或者任何从 RuntimeException 继承的异常），它们也被称为“不受检查异常”。
        // 这种异常属于错误，将被自动捕获，就不用你亲自动手了。
        // 要是自己去检查 RuntimeException 的话，代码就显得太混乱了。
        // 不过尽管通常不用捕获 RuntimeException 异常，但还是可以在代码中抛出 RuntimeException 类型的异常。
        throw new RuntimeException("From f()");
    }
    static void g(){
        f();
    }
    public static void main(String[] args) {
        g();
    }
}
