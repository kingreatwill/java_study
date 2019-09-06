package threadTest;


import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolExecutorTest {
    @Test
    public void test1(){
        ExecutorService fixedExecutorService = Executors.newFixedThreadPool(1);
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) fixedExecutorService;
        System.out.println(threadPoolExecutor.getMaximumPoolSize());
        threadPoolExecutor.setCorePoolSize(8);

        ExecutorService singleExecutorService = Executors.newSingleThreadExecutor();
//      运行时异常 java.lang.ClassCastException
//      ThreadPoolExecutor threadPoolExecutor2 = (ThreadPoolExecutor) singleExecutorService;//ScheduledThreadPoolExecutor

    }

    @Test
    public void test2() throws InterruptedException {
        new ThreadTest().test2();
        Thread.sleep(1000*10); //阻塞主线程
    }

    public  class ThreadTest {
        public void test2() throws InterruptedException {
            int corePoolSize = 2;
            int maximumPoolSize = 4;
            long keepAliveTime = 10;
            TimeUnit unit = TimeUnit.SECONDS;
            BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);
            ThreadFactory threadFactory = new NameTreadFactory();
            RejectedExecutionHandler handler = new MyIgnorePolicy();
            ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit,
                    workQueue, threadFactory, handler);
            executor.prestartAllCoreThreads(); // 预启动所有核心线程

            for (int i = 1; i <= 10; i++) {
                MyTask task = new MyTask(String.valueOf(i));
                executor.execute(task);
            }

            Thread.sleep(1000*10); //阻塞主线程
        }
         class NameTreadFactory implements ThreadFactory {

            private final AtomicInteger mThreadNum = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r, "my-thread-" + mThreadNum.getAndIncrement());

                System.out.println(t.getName() + " has been created");
                return t;
            }
        }

        public  class MyIgnorePolicy implements RejectedExecutionHandler {

            public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
                doLog(r, e);
            }

            private void doLog(Runnable r, ThreadPoolExecutor e) {
                // 可做日志记录等
                System.err.println( r.toString() + " rejected");
//          System.out.println("completedTaskCount: " + e.getCompletedTaskCount());
            }
        }

         class MyTask implements Runnable {
            private String name;

            public MyTask(String name) {
                this.name = name;
            }

            @Override
            public void run() {
                try {
                    System.out.println(this.toString() + " is running!");
                    Thread.sleep(3000); //让任务执行慢点
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            public String getName() {
                return name;
            }

            @Override
            public String toString() {
                return "MyTask [name=" + name + "]";
            }
        }
    }




    // https://www.jianshu.com/p/c41e942bcd64
    // https://www.jianshu.com/p/f030aa5d7a28
    /*
    public ThreadPoolExecutor(int corePoolSize, // 1 核心线程池大小
                              int maximumPoolSize,  // 2 最大线程池大小
                              long keepAliveTime,  // 3 线程最大空闲时间
                              TimeUnit unit,  // 4 时间单位
                              BlockingQueue<Runnable> workQueue, // 5 线程等待队列
                              ThreadFactory threadFactory,  // 6 线程创建工厂
                              RejectedExecutionHandler handler ) { //7 拒绝策略
        if (corePoolSize < 0 ||
                maximumPoolSize <= 0 ||
                maximumPoolSize < corePoolSize ||
                keepAliveTime < 0)
            throw new IllegalArgumentException();
        if (workQueue == null || threadFactory == null || handler == null)
            throw new NullPointerException();
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.workQueue = workQueue;
        this.keepAliveTime = unit.toNanos(keepAliveTime);
        this.threadFactory = threadFactory;
        this.handler = handler;
    }*/

    @Test
    public void testMypool() throws InterruptedException {
        new MyThreadPoolExecutor(1,1,10,TimeUnit.SECONDS,new SynchronousQueue<Runnable>() ).execute(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("12313");
                    }
                }
        );
        TimeUnit.SECONDS.sleep(1000*10);
    }

    public class MyThreadPoolExecutor extends ThreadPoolExecutor{

        public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        @Override
        public void execute(Runnable command){
            super.execute(command);
        }

        @Override
        public void beforeExecute(Thread t, Runnable r) {
            System.out.println("beforeExecute");
        }

        @Override
        public void afterExecute(Runnable r, Throwable t) {
            System.out.println("afterExecute");
        }
    }


}
