package file_demo;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class WritingFile_demo {
    static Random rand = new Random(47);
    static final int SIZE = 1000;
    public static void
    main(String[] args) throws Exception {
        // Write bytes to a file:
        byte[] bytes = new byte[SIZE];
        rand.nextBytes(bytes);
        Files.write(Paths.get("helloworld\\src\\test\\java\\file_demo\\WritingFile_demo.dat"), bytes);
        System.out.println("bytes.dat: " +
                Files.size(Paths.get("helloworld\\src\\test\\java\\file_demo\\WritingFile_demo.dat")));

        // Write an iterable to a file:
        List<String> lines = Files.readAllLines(
                Paths.get("helloworld\\src\\test\\java\\file_demo\\WritingFile_demo.java"));
        Files.write(Paths.get("helloworld\\src\\test\\java\\file_demo\\WritingFile_demo.write"), lines);
        System.out.println("WritingFile_demo.write: " +
                Files.size(Paths.get("helloworld\\src\\test\\java\\file_demo\\WritingFile_demo.write")));
    }
}
