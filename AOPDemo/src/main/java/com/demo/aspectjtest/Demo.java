package com.demo.aspectjtest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

// https://blog.csdn.net/qq_15037231/article/details/83106006
// https://blog.csdn.net/X18621144576/article/details/80023648
public class Demo {
    public void sayHello(){
        System.out.println("hello world !");
    }

    @Before("execution(* com.demo.aspectjtest.on**(..))")
    public void onActivityMethodBefore(JoinPoint joinPoint) throws Throwable {
        System.out.println("JoinPoint !");
    }


    @DebugTrace
    public void sayHello2(){
        System.out.println("hello world 2222!");
    }
    @Pointcut("execution(@com.demo.aspectjtest.DebugTrace * *..*.*(..))")
    public void DebugTraceMethod() {}

    @Before("DebugTraceMethod()")
    public void beforeDebugTraceMethod(JoinPoint joinPoint) throws Throwable {
        String key = joinPoint.getSignature().toString();
        System.out.println(key);
    }

    public static void main(String args[]){
        Demo helloWord =new Demo();
        helloWord.sayHello2();
    }
}
//AspectJ所认可的JoinPoints的类型
//Join Points               说明                              示例
//method call               函数调用                        比如调用Log.e()，这是一处JPoint
//method execution          函数执行                        比如Log.e()的执行内部，是一处Join Points。注意它和method call的区别。method                                                                        call是调用某个函数的地方。而execution是某个函数执行的内部。
//constructor call          构造函数调用                      和method call类似
//constructor execution     构造函数执行                      和method execution类似
//field get                 获取某个变量                      比如读取DemoActivity.debug成员
//field set                 设置某个变量                      比如设置DemoActivity.debug成员
//pre-initialization        Object在构造函数中的一些工作
//initialization            Object在构造函数中做的工作
//static initialization     类初始化                        比如类的static{}
//handler                   异常处理                        比如try catch(xxx)中，对应catch内的执行
//advice execution          这个是AspectJ的内容