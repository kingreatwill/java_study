package CollectionsTest.Set;

import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class LinkedHashSetTest {

    /*
    * 我们知道HashSet保证元素唯一，可是元素存放进去是没有顺序的，那么我们要保证有序，怎么办呢？
    *  在HashSet下面有一个子类 java.util.LinkedHashSet ，它是链表和哈希表组合的一个数据存储结构
    * */
    @Test
    public void test(){
        Set<String> set = new LinkedHashSet<String>();
        set.add("bbb");
        set.add("aaa");
        set.add("abc");
        set.add("bbc");
        set.add("222");
        set.add("111");
        for (String s : set) {
            System.out.println(s);
        }
    }
}
