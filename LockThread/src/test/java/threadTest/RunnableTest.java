package threadTest;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class RunnableTest {


    // Runnable是接口,Thread 是Runnable的实现;
    public class MyThread  implements Runnable {
        public void run() {
            System.out.println("MyThread.run()");
        }
    }
    @Test
    public void test() throws InterruptedException {
        MyThread myThread = new MyThread();
        Thread thread = new Thread(myThread);
        thread.start();
        TimeUnit.SECONDS.sleep(10);
    }

}