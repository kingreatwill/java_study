package concurrentTest;

import org.junit.Test;

import java.util.concurrent.PriorityBlockingQueue;

/*
* PriorityBlockingQueue 是二叉堆结构的无界优先级阻塞队列，
* 使用显示锁 Lock 保证线程安全，是一个线程安全的 PriorityQueue。
* 元素的优先级顺序通过 Comparator 实现，内部不可添加不可比较的值。相较于我们前几章所讲的Queue，
* PriorityBlockingQueue  算是一个老牌的队列了，从JDK1.5加入JUC行列，
* 如果我们需要对队列进行优先级排序，PriorityBlockingQueue 将是一个不错的选择。

* */
public class PriorityBlockingQueueTest {
    @Test
    public void test1() {
        PriorityBlockingQueue<Integer> priorityBlockingQueue =new PriorityBlockingQueue<>();
        priorityBlockingQueue.offer(5);
        priorityBlockingQueue.offer(8);
        priorityBlockingQueue.offer(1);
        System.out.println(priorityBlockingQueue.poll());
        System.out.println(priorityBlockingQueue.poll());
        System.out.println(priorityBlockingQueue.poll());
    }
}
