package concurrentTest;

import org.junit.Test;

import java.util.concurrent.LinkedTransferQueue;

/*
* LinkedTransferQueue 是单向链表结构的无界阻塞队列， 从JDK1.7开始加入到J.U.C的行列中。
* 通过 CAS 和 LockSupport 实现线程安全，元素操作按照 FIFO (first-in-first-out 先入先出) 的顺序。
* 内存一致性遵循对LinkedTransferQueue的插入操作先行发生于(happen-before)访问或移除操作

* 特点：
* LinkedTransferQueue（后称LTQ） 采用一种预占模式。
* 意思就是消费者线程取元素时，如果队列为空，那就生成一个节点（节点元素为null）入队，
* 然后消费者线程被等待在这个节点上，后面生产者线程入队时发现有一个元素为null的节点，生产者线程就不入队了，
* 直接就将元素填充到该节点，并唤醒该节点等待的线程，被唤醒的消费者线程取走元素，从调用的方法返回。我们称这种节点操作为“匹配”方式。

* */
public class LinkedTransferQueueTest {
    @Test
    public void test1() throws InterruptedException {
        LinkedTransferQueue<Integer> linkedTransferQueue =new LinkedTransferQueue<>();
        // System.out.println(linkedTransferQueue.take()); // 阻塞
        linkedTransferQueue.offer(1);
        linkedTransferQueue.add(5);
        System.out.println(linkedTransferQueue.take());
        System.out.println(linkedTransferQueue.take());
    }
}
