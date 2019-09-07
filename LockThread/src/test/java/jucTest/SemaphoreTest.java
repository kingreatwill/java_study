package jucTest;

import org.junit.Test;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    @Test
    public void test(){
        Semaphore semaphore = new Semaphore(1);// 同步关键类，构造方法传入的数字是多少，则同一个时刻，只运行多少个进程同时运行制定代码
        semaphore.acquire(1);
        semaphore.release(1);
    }
}
