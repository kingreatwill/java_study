package threadTest;

import org.junit.Test;

/*
* 实现线程的方式
1 继承thread类
2 实现runnable接口
3 实现Callable接口通过FutureTask包装器来创建Thread线程
* */
public class ThreadTest {




    @Test
    public void testJoin() throws InterruptedException {
        MyThread thread = new MyThread();
        thread.start();
        thread.join(); //方法 join() 的作用是等待线程对象销毁。
        // thread.wait();
        System.out.println(" 我想在 thread 执行完之后执行，我做到了 ");
    }
    public class MyThread extends Thread{
        @Override
        public void run() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" 执行完毕 ");
        }

    }
}

