package MapsTest;

import java.util.Enumeration;
import java.util.Hashtable;
import org.junit.Test;

/*
1. HashMap几乎可以等价于Hashtable，除了HashMap是非synchronized的，并可以接受null(HashMap可以接受为null的键值(key)和值(value)，而Hashtable则不行)。
2. HashMap是非synchronized，而Hashtable是synchronized，这意味着Hashtable是线程安全的，多个线程可以共享一个Hashtable；而如果没有正确的同步的话，多个线程是不能共享HashMap的。Java 5提供了ConcurrentHashMap，它是HashTable的替代，比HashTable的扩展性更好。
3. 另一个区别是HashMap的迭代器(Iterator)是fail-fast迭代器，而Hashtable的enumerator迭代器不是fail-fast的。所以当有其它线程改变了HashMap的结构（增加或者移除元素），将会抛出ConcurrentModificationException，但迭代器本身的remove()方法移除元素则不会抛出ConcurrentModificationException异常。但这并不是一个一定发生的行为，要看JVM。这条同样也是Enumeration和Iterator的区别。
4. 由于Hashtable是线程安全的也是synchronized，所以在单线程环境下它比HashMap要慢。如果你不需要同步，只需要单一线程，那么使用HashMap性能要好过Hashtable。
5. HashMap不能保证随着时间的推移Map中的元素次序是不变的。
**/
public class HashTableTest {
    @Test
    public void test1(){
        Hashtable<String,String> hashTable =new Hashtable<String,String>();
        hashTable.put("","1");
        hashTable.put("8","2");
        hashTable.put("9","3");
        hashTable.put("7","3");
        Enumeration  names = hashTable.keys();
        while(names.hasMoreElements()) {
            String str = (String) names.nextElement();
            System.out.println(str + ": " +
                    hashTable.get(str));
        }
       // hashTable.put(null,""); // 运行出错
    }
}
