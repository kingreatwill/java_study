package regular_expression;

import java.util.Arrays;

public class IntegerMatch {
    public static String knights =
            "Then, when you have found the shrubbery, " +
                    "you must cut down the mightiest tree in the " +
                    "forest...with... a herring!";
    public static void split(String regex) {
        System.out.println(
                Arrays.toString(knights.split(regex)));
    }
    public static void main(String[] args) {
        //  \d   表示一位数字  \\d
        // +    表示一个或多个
        // -?   可能有-号，也可能没有-号
        // |  表示或操作
        // []  也是或
        // . 任意一个字符
        // * 任意（0）多个字符
        System.out.println("-1234".matches("-?\\d+")); //\d表示一位数字
        System.out.println("5678".matches("-?\\d+"));
        System.out.println("+911".matches("\\+?\\d+"));
        System.out.println("911".matches("\\+?\\d+"));
        System.out.println("+911".matches("-?\\d+"));
        System.out.println("+911".matches("(-|\\+)?\\d+"));
        System.out.println("+911".matches("[-+]?\\d+"));
        System.out.println("+911".matches("[-+]\\d+"));

        System.out.println("a".matches("[abc[hij]]")); // 只能匹配 a b c h   i   j, 合集
        System.out.println("h".matches("[a-z&&[hij]]")); // 只能匹配 h   i   j, 交集
        // 大写W 非单词字符
        // 小写w 单词字符
        split(" "); // Doesn't have to contain regex chars
        split("\\W+"); // Non-word characters
        split("n\\W+"); // 'n' followed by non-words

        System.out.println(
                knights.replaceFirst("f\\w+", "located"));// 以f开头的单词替换成located
        System.out.println(
                knights.replaceAll("shrubbery|tree|herring","banana"));
    }
}
