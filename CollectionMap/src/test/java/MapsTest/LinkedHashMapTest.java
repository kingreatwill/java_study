package MapsTest;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapTest {
    @Test
    public void test1(){
        LinkedHashMap<String, Object> hasMap = new LinkedHashMap<String, Object>();
        hasMap.put("name", "zhangsan");
        hasMap.put("age", 20);
        hasMap.put("addr", "北京市");
        hasMap.put(null, null);
        hasMap.put("info", null);
        hasMap.put(null, "who");

        for (Map.Entry<String, Object> entry : hasMap.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
        /*
        * 根据输出我们可以得出以下几个结论：
LinkedHashMap的输入顺序和输出顺序是一致的。
LinkedHashMap允许Key和Value都可以null
LinkedHashMap中添加元素时，如果Key重复，则后添加的会覆盖前面已经存在的值
        * */
    }

    @Test
    public void test2(){
        LRUCache<Integer, String> cache = new LRUCache<>();
        cache.put(1, "a");
        cache.put(2, "b");
        cache.put(3, "c");
        cache.get(1);
        cache.put(4, "d");
        System.out.println(cache.keySet());
        /*
        *  设定最大缓存空间 MAX_ENTRIES 为 3；
        使用 LinkedHashMap 的构造函数将 accessOrder 设置为 true，开启 LRU 顺序；
        覆盖 removeEldestEntry() 方法实现，在节点多于 MAX_ENTRIES 就会将最近最久未使用的数据移除。
        * */
    }
    class LRUCache<K, V> extends LinkedHashMap<K, V> {
        private static final int MAX_ENTRIES = 3;

        protected boolean removeEldestEntry(Map.Entry eldest) {
            return size() > MAX_ENTRIES;
        }

        LRUCache() {
            super(MAX_ENTRIES, 0.75f, true);
        }
    }

}

/*
* LinkedHashMap
存储结构
继承自 HashMap，因此具有和 HashMap 一样的快速查找特性。

public class LinkedHashMap<K,V> extends HashMap<K,V> implements Map<K,V>
内部维护了一个双向链表，用来维护插入顺序或者 LRU 顺序。


transient LinkedHashMap.Entry<K,V> head;


    transient LinkedHashMap.Entry<K,V> tail;
    accessOrder 决定了顺序，默认为 false，此时维护的是插入顺序。

final boolean accessOrder;
        LinkedHashMap 最重要的是以下用于维护顺序的函数，它们会在 put、get 等方法中调用。

        void afterNodeAccess(Node<K,V> p) { }
        void afterNodeInsertion(boolean evict) { }
        afterNodeAccess()
        当一个节点被访问时，如果 accessOrder 为 true，则会将该节点移到链表尾部。也就是说指定为 LRU 顺序之后，在每次访问一个节点时，会将这个节点移到链表尾部，保证链表尾部是最近访问的节点，那么链表首部就是最近最久未使用的节点。

        void afterNodeAccess(Node<K,V> e) { // move node to last
        LinkedHashMap.Entry<K,V> last;
        if (accessOrder && (last = tail) != e) {
        LinkedHashMap.Entry<K,V> p =
        (LinkedHashMap.Entry<K,V>)e, b = p.before, a = p.after;
        p.after = null;
        if (b == null)
        head = a;
        else
        b.after = a;
        if (a != null)
        a.before = b;
        else
        last = b;
        if (last == null)
        head = p;
        else {
        p.before = last;
        last.after = p;
        }
        tail = p;
        ++modCount;
        }
        }
        afterNodeInsertion()
        在 put 等操作之后执行，当 removeEldestEntry() 方法返回 true 时会移除最晚的节点，也就是链表首部节点 first。

        evict 只有在构建 Map 的时候才为 false，在这里为 true。

        void afterNodeInsertion(boolean evict) { // possibly remove eldest
        LinkedHashMap.Entry<K,V> first;
        if (evict && (first = head) != null && removeEldestEntry(first)) {
        K key = first.key;
        removeNode(hash(key), key, null, false, true);
        }
        }
        removeEldestEntry() 默认为 false，如果需要让它为 true，需要继承 LinkedHashMap 并且覆盖这个方法的实现，这在实现 LRU 的缓存中特别有用，通过移除最近最久未使用的节点，从而保证缓存空间足够，并且缓存的数据都是热点数据。

protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
        return false;
        }
        LRU 缓存
        以下是使用 LinkedHashMap 实现的一个 LRU 缓存：

        设定最大缓存空间 MAX_ENTRIES 为 3；
        使用 LinkedHashMap 的构造函数将 accessOrder 设置为 true，开启 LRU 顺序；
        覆盖 removeEldestEntry() 方法实现，在节点多于 MAX_ENTRIES 就会将最近最久未使用的数据移除。
class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private static final int MAX_ENTRIES = 3;

    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > MAX_ENTRIES;
    }

    LRUCache() {
        super(MAX_ENTRIES, 0.75f, true);
    }
}
    public static void main(String[] args) {
        LRUCache<Integer, String> cache = new LRUCache<>();
        cache.put(1, "a");
        cache.put(2, "b");
        cache.put(3, "c");
        cache.get(1);
        cache.put(4, "d");
        System.out.println(cache.keySet());
    }
[3, 1, 4]
* */
