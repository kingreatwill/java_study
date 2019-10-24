package file_demo;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PathWatcher {
    static Path test = Paths.get("D:/test");

    static void delTxtFiles() {
        try {
            Files.walk(test)
                    .filter(f ->
                            f.toString()
                                    .endsWith(".txt"))
                    .forEach(f -> {
                        try {
                            System.out.println("deleting " + f);
                            Files.delete(f);
                        } catch(IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
       // Files.createFile(test.resolve("Hello.txt"));
        WatchService watcher = FileSystems.getDefault().newWatchService();
        test.register(watcher, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
        Executors.newSingleThreadScheduledExecutor()
                .schedule(PathWatcher::delTxtFiles,
                        250, TimeUnit.MILLISECONDS);
        WatchKey key = watcher.take();//将等待并阻塞在这里。
        for(WatchEvent evt : key.pollEvents()) {
            System.out.println("evt.context(): " + evt.context() +
                    "\nevt.count(): " + evt.count() +
                    "\nevt.kind(): " + evt.kind());
           // System.exit(0);
        }
    }
}
