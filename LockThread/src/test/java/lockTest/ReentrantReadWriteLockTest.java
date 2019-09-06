package lockTest;

import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
* ReentrantReadWriteLock维护了一对相关的锁：共享锁readLock和独占锁writeLock。
* 共享锁readLock用于读操作，能同时被多个线程获取；独占锁writeLock用于写入操作，只能被一个线程持有。
*
* 1. 公平性：

非公平模式：默认模式。一个持续争用的非公平锁，可能会使其他读线程或写线程无限延期，但它比公平锁有更高的吞吐量。
公平模式：当构造一个公平锁时，线程争用使用一个近似顺序到达的策略。当目前持有的锁被释放，要么是等待时间最长的单个写入线程被分配写入锁，或者如果有一组读线程比所有等待写线程等待更长的时间，该组将被分配读取锁。
如果已经持有写锁，或者有一个正在等待写的线程，尝试获取公平读锁的线程（非重入）将会阻塞。在等待时间最长的写线程获取并释放写锁之前，当前线程将不能获取读锁。如果一个等待写入的线程放弃等待，并且在队列中等待时间最长的一个或多个读线程正在等待写锁空闲，那么这些读线程将被分配读取锁。

当读锁和写锁都是空闲时（这意味着已经没有等待线程）， 一个尝试获取公平写锁（非重入）的线程才会获取成功。注意非阻塞方法tryLock()会立即尝试获取锁，它并不会按照公平原则那样去等待前继节点。

2. 重入性：
ReentrantReadWriteLock允许读线程和写线程重复获取读锁或写锁。当所有写锁都被释放，不可重入读线程才允许获取锁。此外，一个写入线程可以获取读锁，但是一个读线程不能获取写锁。

3. 锁降级：
重入性允许从写锁降级到读锁：首先获取写锁，然后获取读锁，然后释放写锁。不过，从一个读锁升级到写锁是不允许的。读锁和写锁在获取过程中都支持中断。

4. Condition支持：
Condition只有在写锁中用到，读锁是不支持Condition的。
* */
public class ReentrantReadWriteLockTest {
    @Test
    public  void test1(){
    //利用重入来执行升级缓存后的锁降级
    }
    class CachedData {
        Object data;
        volatile boolean cacheValid;    //缓存是否有效
        ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

        void processCachedData() {
            rwl.readLock().lock();    //获取读锁
            //如果缓存无效，更新cache;否则直接使用data
            if (!cacheValid) {
                // Must release read lock before acquiring write lock
                //获取写锁前须释放读锁
                rwl.readLock().unlock();
                rwl.writeLock().lock();
                // Recheck state because another thread might have acquired
                //   write lock and changed state before we did.
                if (!cacheValid) {
                    //data = ...
                    cacheValid = true;
                }
                // Downgrade by acquiring read lock before releasing write lock
                //锁降级，在释放写锁前获取读锁
                rwl.readLock().lock();
                rwl.writeLock().unlock(); // Unlock write, still hold read
            }

           // use(data);
            rwl.readLock().unlock();    //释放读锁
        }
    }
    @Test
    public  void test2(){
//使用 ReentrantReadWriteLock 来提高 Collection 的并发性
//
//　　　　通常在 collection 数据很多，读线程访问多于写线程并且 entail 操作的开销高于同步开销时尝试这么做。
    }

    class RWDictionary {
        private final Map<String, Object> m = new TreeMap<String, Object>();
        private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
        private final Lock r = rwl.readLock();    //读锁
        private final Lock w = rwl.writeLock();    //写锁

        public Object get(String key) {
            r.lock();
            try { return m.get(key); }
            finally { r.unlock(); }
        }
        public Object[] allKeys() {
            r.lock();
            try { return m.keySet().toArray(); }
            finally { r.unlock(); }
        }
        public Object put(String key, Object value) {
            w.lock();
            try { return m.put(key, value); }
            finally { w.unlock(); }
        }
        public void clear() {
            w.lock();
            try { m.clear(); }
            finally { w.unlock(); }
        }
    }

    @Test
    public  void test3(){

    }
}
