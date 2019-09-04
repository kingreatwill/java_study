package jucTest;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchTest {
    @Test
    public void test(){
        testCountDownLatch test = new testCountDownLatch();
        test.runThread();

    }
    public class testCountDownLatch {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        private  void runThread(){
            ExecutorService executorService = Executors.newFixedThreadPool(10);
            for(int i=0;i<10 ;i++){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            countDownLatch.await();
                            System.out.println("Thread:"+Thread.currentThread().getName()+",time: "+System.currentTimeMillis());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
            countDownLatch.countDown();
        }
    }

}
