package file_demo;

import java.nio.file.Files;
import java.nio.file.Paths;

// 注释;1
// 跳过注释行，其余的内容每行只打印一半。
public class ReadFile_ListOfLines {
    public static void
    main(String[] args) throws Exception {
 // 注释2;
        Files.readAllLines(
                Paths.get("helloworld\\src\\test\\java\\file_demo\\ReadFile_ListOfLines.java"))
                .stream()
                .filter(line -> !line.startsWith("//"))// 注释3;
//                .map(line ->
//                        line.substring(0, line.length()/2))
                .forEach(System.out::println);
    }
}
