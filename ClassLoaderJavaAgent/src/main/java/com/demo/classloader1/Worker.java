package com.demo.classloader1;

public class Worker {
    public Worker() {
        System.out.println("<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>【重磅消息】我被构造了");
    }

    public void doit() {
        System.out.println(this.getClass().getClassLoader().toString() + "--->----------------->666666  222" );

    }
}
