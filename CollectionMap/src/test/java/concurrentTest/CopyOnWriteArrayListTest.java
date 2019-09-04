package concurrentTest;

import org.junit.Test;

/*
* CopyOnWriteArrayList
读写分离
写操作在一个复制的数组上进行，读操作还是在原始数组中进行，读写分离，互不影响。

写操作需要加锁，防止并发写入时导致写入数据丢失。

写操作结束之后需要把原始数组指向新的复制数组。

public boolean add(E e) {
    final ReentrantLock lock = this.lock;
    lock.lock();
    try {
        Object[] elements = getArray();
        int len = elements.length;
        Object[] newElements = Arrays.copyOf(elements, len + 1);
        newElements[len] = e;
        setArray(newElements);
        return true;
    } finally {
        lock.unlock();
    }
}

final void setArray(Object[] a) {
    array = a;
}
@SuppressWarnings("unchecked")
private E get(Object[] a, int index) {
    return (E) a[index];
}
适用场景
CopyOnWriteArrayList 在写操作的同时允许读操作，大大提高了读操作的性能，因此很适合读多写少的应用场景。

但是 CopyOnWriteArrayList 有其缺陷：

内存占用：在写操作时需要复制一个新的数组，使得内存占用为原来的两倍左右；
数据不一致：读操作不能读取实时性的数据，因为部分写操作的数据还未同步到读数组中。
所以 CopyOnWriteArrayList 不适合内存敏感以及对实时性要求很高的场景。
*
* CopyOnWriteArrayList 是一个线程安全的 ArrayList，通过内部的 volatile 数组和显式锁 ReentrantLock 来实现线程安全
*
*
 和 ArrayList 或 Set 相比，CopyOnWriteArrayList / CopyOnWriteArraySet  拥有以下特性：
适合元素比较少，并且读取操作高于更新(add/set/remove)操作的场景
由于每次更新需要复制内部数组，所以更新操作开销比较大
内部的迭代器 iterator 使用了“快照”技术，存储了内部数组快照， 所以它的 iterator 不支持remove、set、add操作，但是通过迭代器进行并发读取时效率很高。

* */
public class CopyOnWriteArrayListTest {
    @Test
    public void test1(){

    }
}
