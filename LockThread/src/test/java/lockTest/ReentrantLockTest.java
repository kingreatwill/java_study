package lockTest;


import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
* ReentrantLock是一个可重入的互斥锁，也被称为“独占锁”。在上一篇讲解AQS的时候已经提到，“独占锁”在同一个时间点只能被一个线程持有；
* 而可重入的意思是，ReentrantLock可以被单个线程多次获取。
ReentrantLock又分为“公平锁(fair lock)”和“非公平锁(non-fair lock)”。它们的区别体现在获取锁的机制上：在“公平锁”的机制下，线程依次排队获取锁；
* 而“非公平锁”机制下，如果锁是可获取状态，不管自己是不是在队列的head节点都会去尝试获取锁。
*
* ReentrantLock在加锁和内存上提供的语义与内置锁synchronized相同，此外它还提供了一些其他功能，包括定时的锁等待、可中断的锁等待、公平性，以及实现非块结构的加锁。
* 从性能方面来说，在JDK5的早期版本中，ReentrantLock的性能远远好于synchronized，
* 但是从JDK6开始，JDK在synchronized上做了大量优化，使得两者的性能差距不大。synchronized的优点就是简洁。
* 所以说，两者之间的选择还是要看具体的需求，ReentrantLock可以作为一种高级工具，当需要一些高级功能时可以使用它。
* */
public class ReentrantLockTest {

    @Test
    public void test(){
        // 可重入
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        lock.lock();
        try
        {
            //i++;

        }
        finally
        {
            lock.unlock();
            lock.unlock();
        }

    }

    @Test
    public void test1(){
        //1、防止重复执行（忽略重复触发）
        ReentrantLock lock = new ReentrantLock();
        if (lock.tryLock()) {  //如果已经被lock，则立即返回false不会等待，达到忽略操作的效果
            try {
                //操作
            } finally {
                lock.unlock();
            }
        }
    }
    @Test
    public void test2(){
        //2、同步执行，类似synchronized
        //ReentrantLock lock = new ReentrantLock(); //参数默认false，不公平锁
        //一般意义上的锁是不公平的，不一定先来的线程能先得到锁，后来的线程就后得到锁。不公平的锁可能会产生饥饿现象。

        //公平锁的意思就是，这个锁能保证线程是先来的先得到锁。虽然公平锁不会产生饥饿现象，但是公平锁的性能会比非公平锁差很多。
        ReentrantLock lock = new ReentrantLock(true); //公平锁

        lock.lock(); //如果被其它资源锁定，会在此等待锁释放，达到暂停的效果
        try {
            //操作
        } finally {
            lock.unlock();
        }
    }

    @Test
    public void test3(){
        //3、尝试等待执行
        ReentrantLock lock = new ReentrantLock(true); //公平锁
        try {
            if (lock.tryLock(5, TimeUnit.SECONDS)) {
                //如果已经被lock，尝试等待5s，看是否可以获得锁，如果5s后仍然无法获得锁则返回false继续执行
                try {
                    //操作
                } finally {
                    lock.unlock();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace(); //当前线程被中断时(interrupt)，会抛InterruptedException
        }
    }

    @Test
    public void test4() throws InterruptedException {
        //lockInterruptibly()方法比较特殊，当通过这个方法去获取锁时，如果其他线程正在等待获取锁，则这个线程能够响应中断，
        // 即中断线程的等待状态。也就使说，当两个线程同时通过lock.lockInterruptibly()想获取某个锁时，
        // 假若此时线程A获取到了锁，而线程B只有等待，那么对线程B调用threadB.interrupt()方法能够中断线程B的等待过程。
        //【注意是：等待的那个线程B可以被中断，不是正在执行的A线程被中断】
        //4、可中断锁的同步执行
        ReentrantLock lock = new ReentrantLock(true); //公平锁
        lock.lockInterruptibly();
        try {
            //操作
        } finally {
            lock.unlock();
        }
    }
    @Test
    public void test5() throws InterruptedException {
        BussinessClass bc=new BussinessClass();

        Thread t0=new Thread(){
            @Override
            public void run() {
                bc.bFuction();
            }
        };


        Thread t1=new Thread(){
            @Override
            public void run() {
                bc.bFuction();
            }
        };

        String tName=Thread.currentThread().getName();

        System.out.println(tName+"-启动t0！");
        t0.start();

        System.out.println(tName+"-我等个5秒，再启动t1");
        Thread.sleep(5000);
        System.out.println(tName+"-启动t1");
        t1.start();

        System.out.println(tName+"-t1获取不到锁，t0这货睡觉了，没释放，我等个5秒！");
        Thread.sleep(5000);
        System.out.println(tName+"-等了5秒了，不等了，把t1中断了！");
        t1.interrupt();//使中断

        Thread.sleep(Long.MAX_VALUE);
    }
    public class BussinessClass {

        private Lock lock = new ReentrantLock();

        // 业务方法
        public void bFuction() {
            String tName = Thread.currentThread().getName();
            try {
                System.out.println(tName + "-开始获取锁..........");
                lock.lockInterruptibly();

                System.out.println(tName + "-获取到锁了！！！！");
                System.out.println(tName + "-睡觉了，睡个30秒！");
                Thread.sleep(30000);
                System.out.println(tName + "-睡醒了，干活！");
                for (int i = 0; i < 5; i++) {
                    System.out.println(tName + "：" + i);
                }
            } catch (Exception e) {
                System.out.println(tName+"-我好像被中断了！");
                e.printStackTrace();
            }finally{
                lock.unlock();
                System.out.println(tName + "-释放了锁");
            }
        }

    }
}
