package regular_expression;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.MatchResult;

public class ScannerSimpleRead {
    public static BufferedReader input =
            new BufferedReader(new StringReader(
                    "Sir Robin of Camelot\n22 1.61803"));
    public static void main(String[] args) {
        try {
            System.out.println("What is your name?");
            String name = input.readLine();
            System.out.println(name);
            System.out.println("How old are you? " +
                    "What is your favorite double?");
            System.out.println("(input: <age> <double>)");
            String numbers = input.readLine();
            System.out.println(numbers);
            String[] numArray = numbers.split(" ");
            int age = Integer.parseInt(numArray[0]);
            double favorite = Double.parseDouble(numArray[1]);
            System.out.format("Hi %s.%n", name);
            System.out.format("In 5 years you will be %d.%n",
                    age + 5);
            System.out.format("My favorite double is %f.",
                    favorite / 2);
        } catch(IOException e) {
            System.err.println("I/O exception");
        }
        // readLine()方法将一行输入转为String对象。如果每一行数据正好对应一个输入值，那这个方法也还可行。
        // 但是，如果两个输入值在同一行中，事情就不好办了，我们必须分解这个行，才能分别解析所需的输入值。在这个例子中，分解的操作发生在创建numArray时。
        // 像这个： String numbers = input.readLine();
        //            System.out.println(numbers);
        //            String[] numArray = numbers.split(" ");
        {
            Scanner stdin = new Scanner(ScannerSimpleRead.input);
            System.out.println("What is your name?");
            String name = stdin.nextLine();
            System.out.println(name);
            System.out.println(
                    "How old are you? What is your favorite double?");
            System.out.println("(input: <age> <double>)");
            int age = stdin.nextInt();
            double favorite = stdin.nextDouble();
            System.out.println(age);
            System.out.println(favorite);
            System.out.format("Hi %s.%n", name);
            System.out.format("In 5 years you will be %d.%n",
                    age + 5);
            System.out.format("My favorite double is %f.",
                    favorite / 2);

          //  stdin.ioException();
        }
        //没有用try区块捕获IOException。因为，Scanner有一个设假设，在输入结束时会抛出IOException，所以Scanner会把IOException吞掉。
        // 不过，通过ioException()方法，你可以找到最近发生的异常，因此，你可以在必要时检查它。

        //默认情况下，Scanner根据空白字符对输入进行分词
        //我们可以用useDelimiter()来设置分隔符，
        // 同时，还有一个delimiter()方法，用来返回当前正在作为分隔符使用的Pattern对象。
        {
            Scanner scanner = new Scanner("12, 42, 78, 99, 42");
            scanner.useDelimiter("\\s*,\\s*");
            while(scanner.hasNextInt())
                System.out.println(scanner.nextInt());
        }

        //用正则表达式扫描
        {
            String threatData =
                    "58.27.82.161@08/10/2015\n" +
                            "204.45.234.40@08/11/2015\n" +
                            "58.27.82.161@08/11/2015\n" +
                            "58.27.82.161@08/12/2015\n" +
                            "58.27.82.161@08/12/2015\n" +
                            "[Next log section with different data format]";
            Scanner scanner = new Scanner(threatData);
            String pattern = "(\\d+[.]\\d+[.]\\d+[.]\\d+)@" +
                    "(\\d{2}/\\d{2}/\\d{4})";
            while(scanner.hasNext(pattern)) {
                scanner.next(pattern);
                MatchResult match = scanner.match();
                String ip = match.group(1);
                String date = match.group(2);
                System.out.format(
                        "Threat on %s from %s%n", date,ip);
            }
        }

        //StringTokenizer类 StringTokenizer已经可以废弃不用了。
        {
            String input ="But I'm not dead yet! I feel happy!";
            StringTokenizer stoke = new StringTokenizer(input);
            while (stoke.hasMoreElements())
                System.out.print(stoke.nextToken() + " ");
            System.out.println();
            System.out.println(
                    Arrays.toString(input.split(" ")));
            Scanner scanner = new Scanner(input);
            while (scanner.hasNext())
                System.out.print(scanner.next() + " ");
        }
    }
}
