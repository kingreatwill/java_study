package concurrentTest;

import org.junit.Test;

import java.util.concurrent.ConcurrentSkipListMap;

/*
* ConcurrentSkipListMap 是一个线程安全的有序的哈希表，并发安全主要由 CAS 来实现。
* 内部数据存储使用了跳表（Skip List），时间复杂度O(log n)，空间复杂度O(n)。
* */
public class ConcurrentSkipListMapTest {
    @Test
    public void test1(){
        ConcurrentSkipListMap<String,String> concurrentSkipListMap =new ConcurrentSkipListMap<>();
        concurrentSkipListMap.put("132","123");
        concurrentSkipListMap.putIfAbsent("456","456");
        concurrentSkipListMap.putIfAbsent("456","4567");

        System.out.println(concurrentSkipListMap);
    }
}
