package lockTest;


import org.junit.Test;

/*
* ReentrantLock是一个可重入的互斥锁，也被称为“独占锁”。在上一篇讲解AQS的时候已经提到，“独占锁”在同一个时间点只能被一个线程持有；
* 而可重入的意思是，ReentrantLock可以被单个线程多次获取。
ReentrantLock又分为“公平锁(fair lock)”和“非公平锁(non-fair lock)”。它们的区别体现在获取锁的机制上：在“公平锁”的机制下，线程依次排队获取锁；
* 而“非公平锁”机制下，如果锁是可获取状态，不管自己是不是在队列的head节点都会去尝试获取锁。
* */
public class ReentrantLockTest {
    @Test
    public void test1(){

    }
}
