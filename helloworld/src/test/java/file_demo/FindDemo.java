package file_demo;

import java.nio.file.*;

public class FindDemo {
    public static void
    main(String[] args) throws Exception {
        Path test = Paths.get("D:/test");

        //在 matcher 中，glob 表达式开头的 **/ 表示“当前目录及所有子目录”，这在当你不仅仅要匹配当前目录下特定结尾的 Path 时非常有用。
        // 单 * 表示“任何东西”，然后是一个点，然后大括号表示一系列的可能性---我们正在寻找以.tmp 或 .txt 结尾的东西
        // Creating a *directory*, not a file:
        Files.createDirectory(test.resolve("dir.tmp"));
        Files.createFile(test.resolve("ttt.txt"));
        PathMatcher matcher = FileSystems.getDefault()
                .getPathMatcher("glob:**/*.{tmp,txt}");
        Files.walk(test)
                .filter(matcher::matches)
                .forEach(System.out::println);
        System.out.println("***************");

        PathMatcher matcher2 = FileSystems.getDefault()
                .getPathMatcher("glob:*.tmp");
        Files.walk(test)
                .map(Path::getFileName)
                .filter(matcher2::matches)
                .forEach(System.out::println);
        System.out.println("***************");

        Files.walk(test) // Only look for files
               // .filter(Files::isRegularFile)
                .map(Path::getFileName)
                .filter(matcher2::matches)
                .forEach(System.out::println);
    }
}
