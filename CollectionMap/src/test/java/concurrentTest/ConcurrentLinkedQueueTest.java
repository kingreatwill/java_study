package concurrentTest;

import org.junit.Test;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * ConcurrentLinkedQueue 是单向链表结构的无界并发队列。从JDK1.7开始加入到J.U.C的行列中。
 * 使用CAS实现并发安全，元素操作按照 FIFO (first-in-first-out 先入先出) 的顺序。适合“单生产，多消费”的场景。
 * 内存一致性遵循对ConcurrentLinkedQueue的插入操作先行发生于(happen-before)访问或移除操作。

 */
public class ConcurrentLinkedQueueTest {
    @Test
    public void test1(){
        ConcurrentLinkedQueue<String> queue =new ConcurrentLinkedQueue<>();
        queue.offer("321");
        queue.offer("123");
        System.out.println(queue.peek());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }
}
