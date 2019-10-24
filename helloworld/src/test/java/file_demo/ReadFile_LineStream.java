package file_demo;

import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadFile_LineStream {
    public static void
    main(String[] args) throws Exception {
        Files.lines(Paths.get("helloworld\\src\\test\\java\\file_demo\\ReadFile_LineStream.java"))
               //.skip(13) //跳过13
                .findFirst()
                .ifPresent(System.out::println);//ifPresent 如果存在； 如果Optional实例有值则为其调用consumer，否则不做处理
    }
}
