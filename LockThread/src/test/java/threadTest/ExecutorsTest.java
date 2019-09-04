package threadTest;

import org.junit.Test;

import java.util.concurrent.*;

public class ExecutorsTest {
    @Test
    public void test1() throws InterruptedException {
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("12313");
            }
        });
        TimeUnit.SECONDS.sleep(10);
    }

    @Test
    public void test2() throws InterruptedException, ExecutionException {
        // 创建固定数目线程的线程池。
        ExecutorService pool = Executors.newFixedThreadPool(5);
        Future f = pool.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("12313");
            }
        });
        System.out.println(f.get());
        TimeUnit.SECONDS.sleep(10);

        //public static ExecutorService newCachedThreadPool()
        //创建一个可缓存的线程池，调用execute 将重用以前构造的线程（如果线程可用）。如果现有线程没有可用的，则创建一个新线程并添加到池中。终止并从缓存中移除那些已有 60 秒钟未被使用的线程。

        //public static ExecutorService newSingleThreadExecutor()
        //创建一个单线程化的Executor。


        //public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize)
        //创建一个支持定时及周期性的任务执行的线程池，多数情况下可用来替代Timer类。
    }
}
