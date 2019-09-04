package threadTest;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

//返回值的任务必须实现Callable接口。类似的，无返回值的任务必须实现Runnable接口。
public class CallableTest {
    public class SomeCallable<V>  implements Callable<V> {

        @Override
        public V call() throws Exception {
            // TODO Auto-generated method stub
            return null;
        }

    }
    @Test
    public void test() throws ExecutionException, InterruptedException {
        Callable<Integer> oneCallable = new SomeCallable<Integer>();
        //由Callable<Integer>创建一个FutureTask<Integer>对象：
        FutureTask<Integer> oneTask = new FutureTask<Integer>(oneCallable);
        //注释：FutureTask<Integer>是一个包装器，它通过接受Callable<Integer>来创建，它同时实现了Future和Runnable接口。
        //由FutureTask<Integer>创建一个Thread对象：
        Thread oneThread = new Thread(oneTask);
        oneThread.start();
        System.out.println(oneTask.get());
    }
}
