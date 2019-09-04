package MapsTest;

import common.Student;
import org.junit.Test;

import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;

public class WeakHashMapTest {
    @Test
    public void test1(){
        {
            WeakHashMap<Student, String> weakHashMap = new WeakHashMap<>();
            weakHashMap.put(new Student("1", 1), "1");
            System.out.println(weakHashMap);
            System.gc();
            System.out.println(weakHashMap);
        }
        {
            WeakHashMap<String, String> weakHashMap = new WeakHashMap<>();
            weakHashMap.put("1", "1");
            System.out.println(weakHashMap);
            System.gc();
            System.out.println(weakHashMap);
        }
    }

/*
* 1.weakHashMap是基于Key-Value的散列表(数组+链表)，采用拉链法实现的。一般用于单线程当中，非线程安全，weakHashMap中的键是"弱键"。
备注：当"弱键"被GC会收时，它对应的键值也会从weakHashMap中删除。
2.继承于抽象类AbstractMap，并且实现Map接口。
3.默认容量大小是16，加载因子是0.75。
4.最多只允许一条key为Null，允许多条value为Null。
* */

    public final class ConcurrentCache<K, V> {

        private final int size;

        private final Map<K, V> eden;

        private final Map<K, V> longterm;

        public ConcurrentCache(int size) {
            this.size = size;
            this.eden = new ConcurrentHashMap<>(size);
            this.longterm = new WeakHashMap<>(size);
        }

        public V get(K k) {
            V v = this.eden.get(k);
            if (v == null) {
                v = this.longterm.get(k);
                if (v != null)
                    this.eden.put(k, v);
            }
            return v;
        }

        public void put(K k, V v) {
            if (this.eden.size() >= size) {
                this.longterm.putAll(this.eden);
                this.eden.clear();
            }
            this.eden.put(k, v);
        }
    }
}

/*
WeakHashMap其实和HashMap大多数行为是一样的，只是WeakHashMap不会阻止GC回收key对象（不是value），那么WeakHashMap是怎么做到的呢，这就是我们研究的主要问题。

在开始WeakHashMap之前，我们先要对弱引用有一定的了解。

在Java中，有四种引用类型

强引用(Strong Reference)，我们正常编码时默认的引用类型，强应用之所以为强，是因为如果一个对象到GC Roots强引用可到达，就可以阻止GC回收该对象
软引用（Soft Reference）阻止GC回收的能力相对弱一些，如果是软引用可以到达，那么这个对象会停留在内存更时间上长一些。当内存不足时垃圾回收器才会回收这些软引用可到达的对象
弱引用（WeakReference）无法阻止GC回收，如果一个对象时弱引用可到达，那么在下一个GC回收执行时，该对象就会被回收掉。
虚引用（Phantom Reference）十分脆弱，它的唯一作用就是当其指向的对象被回收之后，自己被加入到引用队列，用作记录该引用指向的对象已被销毁
这其中还有一个概念叫做引用队列(Reference Queue)

一般情况下，一个对象标记为垃圾（并不代表回收了）后，会加入到引用队列。
对于虚引用来说，它指向的对象会只有被回收后才会加入引用队列，所以可以用作记录该引用指向的对象是否回收。
* WeakHashMap
存储结构
WeakHashMap 的 Entry 继承自 WeakReference，被 WeakReference 关联的对象在下一次垃圾回收时会被回收。

WeakHashMap 主要用来实现缓存，通过使用 WeakHashMap 来引用缓存对象，由 JVM 对这部分缓存进行回收。

private static class Entry<K,V> extends WeakReference<Object> implements Map.Entry<K,V>
ConcurrentCache
Tomcat 中的 ConcurrentCache 使用了 WeakHashMap 来实现缓存功能。

ConcurrentCache 采取的是分代缓存：

经常使用的对象放入 eden 中，eden 使用 ConcurrentHashMap 实现，不用担心会被回收（伊甸园）；
不常用的对象放入 longterm，longterm 使用 WeakHashMap 实现，这些老对象会被垃圾收集器回收。
当调用 get() 方法时，会先从 eden 区获取，如果没有找到的话再到 longterm 获取，当从 longterm 获取到就把对象放入 eden 中，从而保证经常被访问的节点不容易被回收。
当调用 put() 方法时，如果 eden 的大小超过了 size，那么就将 eden 中的所有对象都放入 longterm 中，利用虚拟机回收掉一部分不经常使用的对象。
public final class ConcurrentCache<K, V> {

    private final int size;

    private final Map<K, V> eden;

    private final Map<K, V> longterm;

    public ConcurrentCache(int size) {
        this.size = size;
        this.eden = new ConcurrentHashMap<>(size);
        this.longterm = new WeakHashMap<>(size);
    }

    public V get(K k) {
        V v = this.eden.get(k);
        if (v == null) {
            v = this.longterm.get(k);
            if (v != null)
                this.eden.put(k, v);
        }
        return v;
    }

    public void put(K k, V v) {
        if (this.eden.size() >= size) {
            this.longterm.putAll(this.eden);
            this.eden.clear();
        }
        this.eden.put(k, v);
    }
}
* */