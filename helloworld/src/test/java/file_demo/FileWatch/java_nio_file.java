package file_demo.FileWatch;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

// 子目录新增和删除 不能监听到，但是子目录新增和删除会触发父级目录的修改;
public class java_nio_file implements Runnable {

    public static void main(String[] args) throws Exception {
        String propFileName = "d:/test"; //要监控的文件目录
        //因为是线程安全的所以可以放入ThreadPool中使用
        ExecutorService cachedThreadPool = Executors.newFixedThreadPool(1);
        cachedThreadPool.execute(new java_nio_file(propFileName));
    }


    public String fileDirectory;
    public static final Logger logger = Logger.getLogger(java_nio_file.class.toString());

    public java_nio_file(String fileDirectory) {
        this.fileDirectory = fileDirectory;
    }

    ExecutorService cachedThreadPool = Executors.newFixedThreadPool(5);

    public void run() {
        WatchService service = null;
        try {
            //获取当前文件系统的监控对象
            service = FileSystems.getDefault().newWatchService();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            //获取文件目录下的Path对象注册到 watchService中。
            //监听的事件类型，有创建，删除，以及修改
            Paths.get(fileDirectory)
                    .register(service, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE,
                            StandardWatchEventKinds.ENTRY_MODIFY);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            WatchKey key = null;
            try {
                //获取可用key.没有可用的就wait
                key = service.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (WatchEvent<?> event : key.pollEvents()) {
                //todo
                logger.info(event.context() + "文件:" + event.kind() + "次数: " + event.count());
            }
            //重置，这一步很重要，否则当前的key就不再会获取将来发生的事件
            boolean valid = key.reset();
            //失效状态，退出监听
            if (!valid) {
                break;
            }
        }

    }
}
