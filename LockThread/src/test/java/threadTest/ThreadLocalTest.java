package threadTest;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

// https://www.infoq.cn/article/K3H2TQlXAyImgNPQ9Dy7
public class ThreadLocalTest {
    @Test
    public void test1() throws InterruptedException {
        ThreadLocal<String> local =new ThreadLocal<>();
        new Thread(new Runnable(){
            @Override
            public void run() {
                local.set("123");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(local.get());
            }
        }).start();
        new Thread(new Runnable(){
            @Override
            public void run() {
                local.set("456");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(local.get());
            }
        }).start();
        TimeUnit.SECONDS.sleep(3);
    }
}
