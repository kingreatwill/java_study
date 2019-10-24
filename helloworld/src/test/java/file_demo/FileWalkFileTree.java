package file_demo;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FileWalkFileTree {
    public static void main(String[] args) throws IOException {
        // "walking"意味着遍历整个子目录和文件。
        Files.walkFileTree(Paths.get("D:/test"), new SimpleFileVisitor<Path>() {
            // 1.**preVisitDirectory()**：在访问目录中条目之前在目录上运行。
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException{
                System.out.println("pre:"+dir);
                return FileVisitResult.CONTINUE;
            }
            // 2.**visitFile()**：运行目录中的每一个文件。
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println(file);
                return FileVisitResult.CONTINUE;
            }
            // 3.  **visitFileFailed()**：调用无法访问的文件。
            //4.**postVisitDirectory()**：在访问目录中条目之后在目录上运行，包括所有的子目录。
            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                System.out.println(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }
}
