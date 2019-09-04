package concurrentTest;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;

/*
*
* 2.对比LinkedBlockingQueue

LinkedBlockingQueue也是一个阻塞式的队列，与ArrayBlockingQueue的区别是什么呢？

LinkedBlockingQueue保存元素的是一个链表。其内部有一个Node的内部类，其中有一个成员变量 Node next。就这样形成了一个链表的结构，要获取下一个元素，只要调用next就可以了。而ArrayBlockingQueue则是一个数组。

LinkedBlockingQueue内部读写(插入获取)各有一个锁，而ArrayBlockingQueue则读写共享一个锁。
* ArrayBlockingQueue和LinkedBlockingQueue的区别：

1. 队列中锁的实现不同

    ArrayBlockingQueue实现的队列中的锁是没有分离的，即生产和消费用的是同一个锁；

    LinkedBlockingQueue实现的队列中的锁是分离的，即生产用的是putLock，消费是takeLock

2. 在生产或消费时操作不同

    ArrayBlockingQueue实现的队列中在生产和消费的时候，是直接将枚举对象插入或移除的；

    LinkedBlockingQueue实现的队列中在生产和消费的时候，需要把枚举对象转换为Node<E>进行插入或移除，会影响性能

3. 队列大小初始化方式不同

    ArrayBlockingQueue实现的队列中必须指定队列的大小；

    LinkedBlockingQueue实现的队列中可以不指定队列的大小，但是默认是Integer.MAX_VALUE



offer

将元素插入队列，成功返回true，如果当前没有可用的空间，则返回false

offer(E e, long timeout, TimeUnit unit)

将元素插入队列，在到达指定的等待时间前等待可用的空间

E poll(long timeout, TimeUnit unit)

获取并移除队列的头部，在指定的等待时间前等待可用的元素

void put(E e)

将元素插入队列，将等待可用的空间（堵塞）

take()

获取并移除队列的头部，在元素变得可用之前一直等待（堵塞）
* */
public class ArrayBlockingQueueTest {
    @Test
    public void test1() throws InterruptedException {
        /*
        *
        * add: 内部实际上获取的offer方法，当Queue已经满了时，抛出一个异常。不会阻塞。
offer:当Queue已经满了时，返回false。不会阻塞。
put:当Queue已经满了时，会进入等待，只要不被中断，就会插入数据到队列中。会阻塞，可以响应中断。
        * */
        class MyThread extends Thread {
            private ArrayBlockingQueue<Integer> abq;
            private MyThread(ArrayBlockingQueue<Integer> abq){
                this.abq =abq;
            }
            public void run() {
                try {
                    Thread.sleep(1000*3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(this.abq.poll());
            }
        }

        ArrayBlockingQueue<Integer> q = new ArrayBlockingQueue<Integer>(2);
        MyThread myThread1 = new MyThread(q);
        myThread1.start();
        q.offer(1);
        q.offer(5);
        q.put(1);
        q.offer(3);
        System.out.println(q);
        System.out.println(q.poll());
        System.out.println(q);

    }
}
