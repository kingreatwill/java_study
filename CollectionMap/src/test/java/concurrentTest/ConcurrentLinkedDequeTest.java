package concurrentTest;

import org.junit.Test;

import java.util.concurrent.ConcurrentLinkedDeque;

/*
* ConcurrentLinkedDeque 是双向链表结构的无界并发队列。
* 从JDK 7开始加入到J.U.C的行列中。使用CAS实现并发安全，与 ConcurrentLinkedQueue 的区别是该阻塞队列同时支持FIFO和FILO两种操作方式，
* 即可以从队列的头和尾同时操作(插入/删除)。适合“多生产，多消费”的场景。
* 内存一致性遵循对 ConcurrentLinkedDeque 的插入操作先行发生于(happen-before)访问或移除操作。
* 相较于 ConcurrentLinkedQueue，ConcurrentLinkedDeque 由于是双端队列，所以在操作和概念上会更加复杂，来一起看下。

* */
public class ConcurrentLinkedDequeTest {
    @Test
    public void test1(){
        {
            //入列
            //CLD的添加方法包括：offer(E)、add(E)、push(E)、addFirst(E)、addLast(E)、offerFirst(E)、offerLast(E)，
            // 所有这些操作都是通过linkFirst(E)或linkLast(E)来实现的。
            ConcurrentLinkedDeque<String> concurrentLinkedDeque = new ConcurrentLinkedDeque<>();
            concurrentLinkedDeque.offer("123");
            concurrentLinkedDeque.offer("222");
            concurrentLinkedDeque.offer("321");
            System.out.println(concurrentLinkedDeque.pollFirst());
            System.out.println(concurrentLinkedDeque.pollLast());
            //出列
            //CLD的获取方法分两种：
            //获取节点：peek、peekFirst 、peekLast、getFirst、getLast，都是通过peekFirst 、peekLast实现。
            //获取并移除节点： poll、pop、remove、pollFirst、pollLast、removeFirst、removeLast，都是通过pollFirst、pollLast实现。
            //pollFirst、pollLast包括了peekFirst 、peekLast的实现，都是找到并返回 first/last 节点，
            // 不同的是，pollFirst、pollLast比peekFirst 、peekLast多了 unlink 这一步

        }

    }
}
