package concurrentTest;

import org.junit.Test;

/*
LinkedBlockingDeque是一个由链表结构组成的双向阻塞队列，即可以从队列的两端插入和移除元素。
双向队列因为多了一个操作队列的入口，在多线程同时入队时，也就减少了一半的竞争。
相比于其他阻塞队列，LinkedBlockingDeque多了addFirst、addLast、peekFirst、peekLast等方法，以first结尾的方法，表示插入、获取获移除双端队列的第一个元素。
以last结尾的方法，表示插入、获取获移除双端队列的最后一个元素。
* */
public class LinkedBlockingDequeTest {
    @Test
    public void test1(){

    }
}
