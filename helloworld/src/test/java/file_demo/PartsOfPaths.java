package file_demo;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PartsOfPaths {
    public static void main(String[] args) {
        System.out.println(System.getProperty("os.name"));
        Path p = Paths.get("PartsOfPaths.java").toAbsolutePath();
        System.out.println(p);
        for(int i = 0; i < p.getNameCount(); i++)
            System.out.println(p.getName(i));
        System.out.println("ends with '.java': " +
                p.endsWith(".java"));//请注意，即使路径以 .java结尾，使用endsWith() 方法也会返回false。这是因为使用endsWith() 比较的是整个路径部分,如PartsOfPaths.java。

        System.out.println("ends with 'PartsOfPaths.java': " +
                p.endsWith("PartsOfPaths.java"));
        for(Path pp : p) {
            System.out.print(pp + ": ");
            System.out.print(p.startsWith(pp) + " : ");
            System.out.println(p.endsWith(pp));
        }
        System.out.println("Starts with " + p.getRoot() + " " + p.startsWith(p.getRoot()));
    }
}
