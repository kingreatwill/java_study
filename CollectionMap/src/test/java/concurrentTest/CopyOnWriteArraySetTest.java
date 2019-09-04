package concurrentTest;

import org.junit.Test;

/*
* CopyOnWriteArraySet 是线程安全的 Set，
* 它是由 CopyOnWriteArrayList 实现，内部持有一个 CopyOnWriteArrayList 引用，
* 所有的操作都是由 CopyOnWriteArrayList 来实现的，区别就是 CopyOnWriteArraySet 是无序的，
* 并且不允许存放重复值。由于是一个Set，所以也不支持随机索引元素

* */
public class CopyOnWriteArraySetTest {
    @Test
    public void test1(){

    }
}
