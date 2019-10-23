package com.demo.hdemo1;

public class LostMessage2 {
    //如果运行这个程序，就会看到即使方法里抛出了异常，它也不会产生任何输出。
    public static void main(String[] args) {
        try {
            System.out.println(getInt());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    static int getInt(){
        try {
            throw new RuntimeException();
        } finally {
            // Using 'return' inside the finally block
            // will silence any thrown exception.
            return 2;
        }
    }

}
