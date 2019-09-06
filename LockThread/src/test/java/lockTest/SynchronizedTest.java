package lockTest;

import org.junit.Test;

import java.util.concurrent.TimeUnit;


// https://www.cnblogs.com/Java-Starter/p/11088977.html
public class SynchronizedTest {
    // synchronized锁重入
    //1.普通同步方法（实例方法），锁是当前实例对象 ，进入同步代码前要获得当前实例的锁
    //2.静态同步方法，锁是当前类的class对象 ，进入同步代码前要获得当前类对象的锁
    //3.同步方法块，锁是括号里面的对象，对给定对象加锁，进入同步代码库前要获得给定对象的锁。
    @Test
    public void test1() throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized ("123")
                {
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("1线程名称为：" + Thread.currentThread().getName());
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized ("456")
                {
                    System.out.println("2线程名称为：" + Thread.currentThread().getName());
                }
            }
        }).start();
        TimeUnit.SECONDS.sleep(20);
    }

    @Test
    public void test2() throws InterruptedException {
        SyncTest syncTest1 = new SyncTest();
        SyncTest syncTest2 = new SyncTest();
        Thread thread1 = new Thread(syncTest1, "thread1");
        Thread thread2 = new Thread(syncTest2, "thread2");
        thread1.start();
        thread2.start();
        TimeUnit.SECONDS.sleep(10);
    }

    public static class SyncTest implements Runnable {
        //共享资源变量
        static int count = 0;

        @Override
        public  void run() {
            increaseCount();
        }

        private synchronized static void increaseCount() {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + ":" + count++);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
