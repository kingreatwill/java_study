import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.stream.Collectors;

public class StringsTest {

    @Test
    public void test1(){
        String s ="Ab@1 5hJ";
        System.out.println(s);
        System.out.println(s.toUpperCase());

    }
    @Test
    public void test2(){
        String mango = "mango";
        String s = "abc" + mango + "def" + 47;
        System.out.println(s);

        // javap -c Concatenation 可以用JDK自带的javap工具来反编译以上代码
        // javap -c WitherStringBuilder

// 相当于
//        StringBuilder sb = new StringBuilder("mango");
//        sb.append("abc");
// ...
//        sb.toString();

        // StringBuffer 线程安全
        // StringBuilder Java SE5引入的 线程不安全 更快
    }

    @Test
    public void test3(){
        System.out.println(string1());
        System.out.println(string2());
    }
    public static String string1() {
        Random rand = new Random(47);
        StringBuilder result = new StringBuilder("[");
        for(int i = 0; i < 25; i++) {
            result.append(rand.nextInt(100));
            result.append(", ");
        }
        result.delete(result.length()-2, result.length());
        result.append("]");
        return result.toString();
    }
    public static String string2() {
        String result = new Random(47)
                .ints(25, 0, 100)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(", "));
        return "[" + result + "]";
    }

    @Test
    public void test4(){
        int x = 5;
        double y = 5.332542;
        // The old way:
        System.out.println("Row 1: [" + x + " " + y + "]");
        // The new way:
        System.out.format("Row 1: [%d %f]%n", x, y);
        // or
        System.out.printf("Row 1: [%d %f]%n", x, y);
        // format()和 printf()是等价的
        //String类也有一个static format()方法，可以格式化字符串。
        //String.format()

    }
}


