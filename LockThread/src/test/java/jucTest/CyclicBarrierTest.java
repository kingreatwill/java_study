package jucTest;

import org.junit.Test;

import java.util.concurrent.*;

public class CyclicBarrierTest {

    @Test
    public  void test() throws InterruptedException {
        CyclicBarrierTestInner test = new CyclicBarrierTestInner();
        test.runThread();
        TimeUnit.SECONDS.sleep(10);
    }
    public class CyclicBarrierTestInner {
        boolean finalFlg = false;
        /**
         * CyclicBarrier 适用再多线程相互等待，直到到达一个屏障点。并且CyclicBarrier是可重用的。
         */
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10);

        private void runThread() {
            ExecutorService executorService = Executors.newFixedThreadPool(10);
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                executorService.submit(createThread(i));


            }
        }

        private Thread createThread(int i) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        cyclicBarrier.await();
                        System.out.println("Thread:" + Thread.currentThread().getName() + "准备完毕,time:" + System.currentTimeMillis());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.setName("name" + i);
            return thread;
        }


    }
}
