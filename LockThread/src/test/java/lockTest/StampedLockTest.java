package lockTest;

import org.junit.Test;

import java.util.concurrent.locks.StampedLock;

/*
* StampedLock是并发包里面jdk8版本新增的一个锁，该锁提供了三种模式的读写控制，三种模式分别如下：

写锁writeLock，是个排它锁或者叫独占锁，同时只有一个线程可以获取该锁，当一个线程获取该锁后，其它请求的线程必须等待，当目前没有线程持有读锁或者写锁的时候才可以获取到该锁，请求该锁成功后会返回一个stamp票据变量用来表示该锁的版本，当释放该锁时候需要unlockWrite并传递参数stamp。
悲观读锁readLock，是个共享锁，在没有线程获取独占写锁的情况下，同时多个线程可以获取该锁，如果已经有线程持有写锁，其他线程请求获取该读锁会被阻塞。这里讲的悲观其实是参考数据库中的乐观悲观锁的，这里说的悲观是说在具体操作数据前悲观的认为其他线程可能要对自己操作的数据进行修改，所以需要先对数据加锁，这是在读少写多的情况下的一种考虑,请求该锁成功后会返回一个stamp票据变量用来表示该锁的版本，当释放该锁时候需要unlockRead并传递参数stamp。
乐观读锁tryOptimisticRead，是相对于悲观锁来说的，在操作数据前并没有通过CAS设置锁的状态，
* 如果当前没有线程持有写锁，则简单的返回一个非0的stamp版本信息，获
* 取该stamp后在具体操作数据前还需要调用validate验证下该stamp是否已经不可用，
* 也就是看当调用tryOptimisticRead返回stamp后到到当前时间间是否有其他线程持有了写锁，
* 如果是那么validate会返回0，否者就可以使用该stamp版本的锁对数据进行操作。
* 由于tryOptimisticRead并没有使用CAS设置锁状态所以不需要显示的释放该锁。
* 该锁的一个特点是适用于读多写少的场景，因为获取读锁只是使用与或操作进行检验，
* 不涉及CAS操作，所以效率会高很多，但是同时由于没有使用真正的锁，在保证数据一致性上需要拷贝一份要操作的变量到方法栈，
* 并且在操作数据时候可能其他写线程已经修改了数据，而我们操作的是方法栈里面的数据，也就是一个快照，所以最多返回的不是最新的数据，但是一致性还是得到保障的。
* */
// 相比ReentrantLock读写锁，StampedLock通过提供乐观读锁在多线程多写线程少的情况下提供更好的性能，因为乐观读锁不需要进行CAS设置锁的状态而只是简单的测试状态。
public class StampedLockTest {
    @Test
    public void test1(){
        StampedLock sl = new StampedLock();
        // 1. 排它锁-写锁（writeLock）
        long stamp = sl.writeLock();
        sl.unlockWrite(stamp);

        // 2.乐观读锁（tryOptimisticRead）
        // 尝试获取乐观读锁（1）
        long stamp2 = sl.tryOptimisticRead();
        // 检查在（1）获取到读锁票据后，锁有没被其他写线程排它性抢占（3）
        if (!sl.validate(stamp2)) {
            // 如果被抢占则获取一个共享读锁（悲观获取）（4）
            stamp = sl.readLock();
            try {

            } finally {
                // 释放共享读锁（6）
                sl.unlockRead(stamp);
            }
        }

        // 3.使用悲观锁获取读锁，并尝试转换为写锁
        // 这里可以使用乐观读锁替换（1）
        long stamp3 = sl.readLock();
        try {
            // 如果当前点在原点则移动（2）
            while (true/*条件*/) {
                // 尝试将获取的读锁升级为写锁（3）
                long ws = sl.tryConvertToWriteLock(stamp3);
                // 升级成功，则更新票据，并设置坐标值，然后退出循环（4）
                if (ws != 0L) {
                    stamp3 = ws;

                    break;
                } else {
                    // 读锁升级写锁失败则释放读锁，显示获取独占写锁，然后循环重试（5）
                    sl.unlockRead(stamp3);
                    stamp3 = sl.writeLock();
                }
            }
        } finally {
            // 释放锁（6）
            sl.unlock(stamp3);
        }
    }
}
