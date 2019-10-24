package file_demo;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathInfo {
    static void show(String id, Object p) {
        System.out.println(id + ": " + p);
    }

    static void info(String name,Path p) {
        System.out.println("********"+name+"**********");
        show("toString", p);
        show("Exists", Files.exists(p));
        show("RegularFile", Files.isRegularFile(p));//常规文件?
        show("Directory", Files.isDirectory(p));
        show("isWritable", Files.isWritable(p));
        show("Absolute", p.isAbsolute()); //绝对的
        show("FileName", p.getFileName());
        show("Parent", p.getParent());
        show("Root", p.getRoot());
        System.out.println("******************");
    }
    public static void main(String[] args) {
        System.out.println(System.getProperty("os.name"));
        info("Paths.get",Paths.get("C:", "path", "to", "nowhere", "NoFile.txt"));
        Path p = Paths.get("helloworld\\src\\test\\java\\file_demo\\PathInfo.java");
        info("helloworld\\src\\test\\java\\file_demo\\PathInfo.java",p);
        Path ap = p.toAbsolutePath();
        info("p.toAbsolutePath()",ap);
        info("ap.getParent()",ap.getParent());
        try {
            info("p.toRealPath()",p.toRealPath());
        } catch(IOException e) {
            System.out.println(e);
        }
        URI u = p.toUri();
        System.out.println("URI: " + u);
        Path puri = Paths.get(u);
        System.out.println(Files.exists(puri));
        File f = ap.toFile(); // Don't be fooled
    }
}
