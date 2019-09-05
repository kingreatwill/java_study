package threadTest;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/*
* Volatile可以看做是一个轻量级的synchronized，它可以在多线程并发的情况下保证变量的“可见性”，
* 什么是可见性？
* 就是在一个线程的工作内存中修改了该变量的值，该变量的值立即能回显到主内存中，从而保证所有的线程看到这个变量的值是一致的。
* 所以在处理同步问题上它大显作用，而且它的开销比synchronized小、使用成本更低。
*
*
在当前的Java内存模型下，线程可以把变量保存在本地内存（比如机器的寄存器）中，而不是直接在主存中进行读写。
* 这就可能造成一个线程在主存中修改了一个变量的值，而另外一个线程还继续使用它在寄存器中的变量值的拷贝，造成数据的不一致。

要解决这个问题，只需要像在本程序中的这样，把该变量声明为volatile（不稳定的）即可，这就指示JVM，这个变量是不稳定的，每次使用它都到主存中进行读取。
* 一般说来，多任务环境下各任务间共享的标志都应该加volatile修饰。

Volatile修饰的成员变量在每次被线程访问时，都强迫从共享内存中重读该成员变量的值。而且，当成员变量发生变化时，强迫线程将变化值回写到共享内存。
* 这样在任何时刻，两个不同的线程总是看到某个成员变量的同一个值。
* */
public class VolatileTest {
    private static boolean bChanged;
    //private static volatile boolean bChanged;
    @Test
    public void test1() throws InterruptedException {
        new Thread() {
            @Override
            public void run() {
                for (;;) {
                    if (bChanged == !bChanged) {
                        System.out.println("!=");
                        System.exit(0);
                    }
                }
            }
        }.start();
        Thread.sleep(1);
        new Thread() {
            @Override
            public void run() {
                for (;;) {
                    bChanged = !bChanged;
                }
            }
        }.start();
        TimeUnit.SECONDS.sleep(10);
    }
    @Test
    public void test2(){

    }

    public static class Singleton {
        private static volatile Singleton instance;

        private Singleton() {
        }

        public static Singleton getInstance() {
            if (instance == null) {
                synchronized (Singleton.class) {
                    if (instance == null) {
                        instance = new Singleton();
                    }
                }
            }
            return instance;
        }
    }

    public class Counter {
        private volatile int count;

        public int getCount(){
            return count;
        }
        public synchronized void increment(){
            count++;
        }
    }
}
