package concurrentTest;

import org.junit.Test;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;

/*
* SynchronousQueue 是一个同步阻塞队列，它的每个插入操作都要等待其他线程相应的移除操作，反之亦然。
* SynchronousQueue 像是生产者和消费者的会合通道，它比较适合“切换”或“传递”这种场景：一个线程必须同步等待另外一个线程把相关信息/时间/任务传递给它。
* 在之后的线程池源码分析中我们也会见到它，所以理解本章对我们之后的线程池讲解也会有很大帮助。

* */
public class SynchronousQueueTest {
    @Test
    public void test1() throws InterruptedException {
        SynchronousQueue<Integer> synchronousQueue =new SynchronousQueue<>();
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        System.out.println(synchronousQueue.take());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        System.out.println(synchronousQueue.poll());
        synchronousQueue.put(1);
        Thread.sleep(100);
    }
}
