package concurrentTest;

import org.junit.Test;

import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;

/*
* ConcurrentSkipListSet 完全是由 ConcurrentSkipListMap 实现，它们俩的关系就像 TreeMap 和TreeSet 。
* ConcurrentSkipListSet 是一个支持并发的有序集合，内部持有一个ConcurrentSkipListMap，所有操作都是通过 ConcurrentSkipListMap 来完成。使用Boolean.TRUE作为map中每个元素的value，map的key作为Set的元素集合（所以不允许存储重复值）。由于是一个Set，所以它不支持随机索引元素。

* */
public class ConcurrentSkipListSetTest {
    @Test
    public void test1(){
        ConcurrentSkipListSet<String> concurrentSkipListSet =new ConcurrentSkipListSet<>();
        concurrentSkipListSet.add("132");
        System.out.println(concurrentSkipListSet.pollLast());
        System.out.println(concurrentSkipListSet);
    }
}
