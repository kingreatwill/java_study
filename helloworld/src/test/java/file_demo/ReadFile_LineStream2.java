package file_demo;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ReadFile_LineStream2 {
    public static void main(String[] args) {
        try(
                Stream<String> input =  Files.lines(Paths.get("helloworld\\src\\test\\java\\file_demo\\ReadFile_LineStream2.java"));
                PrintWriter output =  new PrintWriter("helloworld\\src\\test\\java\\file_demo\\ReadFile_LineStream2.txt")
        ) {
            input
                    .map(String::toUpperCase)
                    .forEachOrdered(output::println);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
