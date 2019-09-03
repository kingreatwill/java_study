package concurrentTest;

/*
1.ConcurrentHashMap 并发版HashMap

最常见的并发容器之一,可以用作并发场景下的缓存。底层依然是哈希表,但在JAVA 8中有了不小的改变,而JAVA 7和JAVA 8都是用的比较多的版本,因此经常会将这两个版本的实现方式做一些比较（比如面试中）。

一个比较大的差异就是,JAVA 7中采用分段锁来减少锁的竞争,JAVA 8中放弃了分段锁,采用CAS（一种乐观锁）,同时为了防止哈希冲突严重时退化成链表（冲突时会在该位置生成一个链表,哈希值相同的对象就链在一起）,会在链表长度达到阈值（8）后转换成红黑树（比起链表,树的查询效率更稳定）。
 */
public class ConcurrentHashMapTest {
}

/*
* ConcurrentHashMap
1. 存储结构
static final class HashEntry<K,V> {
    final int hash;
    final K key;
    volatile V value;
    volatile HashEntry<K,V> next;
}
ConcurrentHashMap 和 HashMap 实现上类似,最主要的差别是 ConcurrentHashMap 采用了分段锁（Segment）,每个分段锁维护着几个桶（HashEntry）,多个线程可以同时访问不同分段锁上的桶,从而使其并发度更高（并发度就是 Segment 的个数）。

Segment 继承自 ReentrantLock。

static final class Segment<K,V> extends ReentrantLock implements Serializable {

    private static final long serialVersionUID = 2249069246763182397L;

    static final int MAX_SCAN_RETRIES =
        Runtime.getRuntime().availableProcessors() > 1 ? 64 : 1;

    transient volatile HashEntry<K,V>[] table;

    transient int count;

    transient int modCount;

    transient int threshold;

    final float loadFactor;
}
final Segment<K,V>[] segments;
默认的并发级别为 16,也就是说默认创建 16 个 Segment。

static final int DEFAULT_CONCURRENCY_LEVEL = 16;

 

2. size 操作
每个 Segment 维护了一个 count 变量来统计该 Segment 中的键值对个数。


transient int count;
    在执行 size 操作时,需要遍历所有 Segment 然后把 count 累计起来。

        ConcurrentHashMap 在执行 size 操作时先尝试不加锁,如果连续两次不加锁操作得到的结果一致,那么可以认为这个结果是正确的。

        尝试次数使用 RETRIES_BEFORE_LOCK 定义,该值为 2,retries 初始值为 -1,因此尝试次数为 3。

        如果尝试的次数超过 3 次,就需要对每个 Segment 加锁。


static final int RETRIES_BEFORE_LOCK = 2;

public int size() {
// Try a few times to get accurate count. On failure due to
// continuous async changes in table, resort to locking.
final Segment<K,V>[] segments = this.segments;
        int size;
        boolean overflow; // true if size overflows 32 bits
        long sum;         // sum of modCounts
        long last = 0L;   // previous sum
        int retries = -1; // first iteration isn't retry
        try {
        for (;;) {
        // 超过尝试次数,则对每个 Segment 加锁
        if (retries++ == RETRIES_BEFORE_LOCK) {
        for (int j = 0; j < segments.length; ++j)
        ensureSegment(j).lock(); // force creation
        }
        sum = 0L;
        size = 0;
        overflow = false;
        for (int j = 0; j < segments.length; ++j) {
        Segment<K,V> seg = segmentAt(segments, j);
        if (seg != null) {
        sum += seg.modCount;
        int c = seg.count;
        if (c < 0 || (size += c) < 0)
        overflow = true;
        }
        }
        // 连续两次得到的结果一致,则认为这个结果是正确的
        if (sum == last)
        break;
        last = sum;
        }
        } finally {
        if (retries > RETRIES_BEFORE_LOCK) {
        for (int j = 0; j < segments.length; ++j)
        segmentAt(segments, j).unlock();
        }
        }
        return overflow ? Integer.MAX_VALUE : size;
        }
        3. JDK 1.8 的改动
        JDK 1.7 使用分段锁机制来实现并发更新操作,核心类为 Segment,它继承自重入锁 ReentrantLock,并发度与 Segment 数量相等。

        JDK 1.8 使用了 CAS 操作来支持更高的并发度,在 CAS 操作失败时使用内置锁 synchronized。

        并且 JDK 1.8 的实现也在链表过长时会转换为红黑树。
* */
