package file_demo;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class RmDirTree {
    public static void rmdir(Path dir) throws IOException {
        // "walking"意味着遍历整个子目录和文件。
        Files.walkFileTree(dir, new SimpleFileVisitor<Path>() {
            // 1.**preVisitDirectory()**：在访问目录中条目之前在目录上运行。
            // 2.**visitFile()**：运行目录中的每一个文件。
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }
            // 3.  **visitFileFailed()**：调用无法访问的文件。
            //4.**postVisitDirectory()**：在访问目录中条目之后在目录上运行，包括所有的子目录。
            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }
}
