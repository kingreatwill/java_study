package threadTest;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class SleepTest {
    @Test
    public void test() throws InterruptedException {
        Thread.sleep(10*1000);
        TimeUnit.SECONDS.sleep(10);
    }
}
