package file_demo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileWalk {
    public static void main(String[] args) throws IOException {
        // "walking"意味着遍历整个子目录和文件。
        Files.walk(Paths.get("D:/test")).forEach((p)->{
            System.out.println(p);
        });
    }
}
