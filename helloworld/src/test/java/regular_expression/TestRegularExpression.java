package regular_expression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegularExpression {
    public static void main(String[] args) {
         args = new String[]{"abcabcabcdefabc","abc+","(abc)+","(abc){2,}"};
        System.out.println("Input: \"" + args[0] + "\"");
        for(String arg : args) {
            System.out.println("Regular expression: \"" + arg + "\"");
            Pattern p = Pattern.compile(arg);
            Matcher m = p.matcher(args[0]);
            while(m.find()) {
                System.out.println(
                        "Match \"" + m.group() + "\" at positions " +
                                m.start() + "-" + (m.end() - 1));
            }
        }
        System.out.println("----------");

        Matcher m = Pattern.compile("\\w+")// 匹配单词
                .matcher("Evening is full of the linnet's wings");
        while(m.find())
            System.out.print(m.group() + " ");
        System.out.println();
        System.out.println("---------");
        int i = 0;
        while(m.find(i)) {
            System.out.print(m.group() + " ");
            i++;
        }
        System.out.println();
        System.out.println("---------");
        m.find(2); // 重新定位
        System.out.println(m.group() + " "); //ening
        m.find();
        System.out.println(m.group() + " "); //is
//        while(m.find(2)) { //死循环 输出 ening
//            System.out.print(m.group() + " ");
//        }
        {
            Matcher m1 = Pattern.compile("[frb][aiu][gx]")
                    .matcher("fix the rug with bags");
            while (m1.find())
                System.out.print(m1.group() + " ");
            System.out.println();
            m1.reset("fix the rig with rags");
            while (m1.find())
                System.out.print(m1.group() + " ");
        }
    }
}
