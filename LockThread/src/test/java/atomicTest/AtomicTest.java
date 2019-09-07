package atomicTest;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {
    private static AtomicInteger count = new AtomicInteger(0);

    public static void increment() {
        count.getAndIncrement();
    }
}
