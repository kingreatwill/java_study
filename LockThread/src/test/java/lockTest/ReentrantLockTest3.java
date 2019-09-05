package lockTest;

import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest3 {
    @Test
    public  void test() throws InterruptedException {
        ReentrantLockTest test1 = new ReentrantLockTest("thread1");
        ReentrantLockTest test2 = new ReentrantLockTest("thread2");

        test1.start();
        test2.start();
        test1.join();
        test2.join();
        System.out.println(ReentrantLockTest.i);
    }
    public static class ReentrantLockTest extends Thread {

        public static ReentrantLock lock = new ReentrantLock();
        public static int i = 0;

        public ReentrantLockTest(String name) {
            super.setName(name);
        }

        @Override
        public void run() {
            for (int j = 0; j < 10000000; j++) {
                lock.lock();
                try {
                    System.out.println(this.getName() + " " + i);
                    i++;
                } finally {
                    lock.unlock();
                }
            }
        }
    }

}
