package regular_expression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
组号为0表示整个表达式，组号1表示被第一对括号括起来的组，以此类推。因此，下面这个表达式，
A(B(C))D
中有三个组：组0是ABCD，组1是BC，组2是C。

Matcher对象提供了一系列方法，用以获取与组相关的信息：

public int groupCount() 返回该匹配器的模式中的分组数目，组0不包括在内。
public String group() 返回前一次匹配操作（例如find()）的第0组（整个匹配）。
public String group(int i) 返回前一次匹配操作期间指定的组号，如果匹配成功，但是指定的组没有匹配输入字符串的任何部分，则将返回null。
public int start(int group) 返回在前一次匹配操作中寻找到的组的起始索引。
public int end(int group) 返回在前一次匹配操作中寻找到的组的最后一个字符索引加一的值。
*/
public class Groups {
    public static final String POEM =
            "Twas brillig, and the slithy toves\n" +
                    "Did gyre and gimble in the wabe.\n" +
                    "All mimsy were the borogoves,\n" +
                    "And the mome raths outgrabe.\n\n" +
                    "Beware the Jabberwock, my son,\n" +
                    "The jaws that bite, the claws that catch.\n" +
                    "Beware the Jubjub bird, and shun\n" +
                    "The frumious Bandersnatch.";
    public static void main(String[] args) {
        {
            Matcher m = Pattern.compile("(?m)(\\S+)\\s+((\\S+)\\s+(\\S+))$")
                    .matcher(POEM);
            while (m.find()) {
                for (int j = 0; j <= m.groupCount(); j++)
                    System.out.print("[" + m.group(j) + "]");
                System.out.println();
            }
        }
        System.out.println("---------");
        {
            Matcher m = Pattern.compile("(\\S+)\\s+((\\S+)\\s+(\\S+))$",Pattern.MULTILINE)
                    .matcher(POEM);
            while (m.find()) {
                for (int j = 0; j <= m.groupCount(); j++)
                    System.out.print("[" + m.group(j) + "]");
                System.out.println();
            }
        }

        System.out.println("-------");

        //它将匹配所有以“java”、“Java”和“JAVA”等开头的行
        Pattern p =  Pattern.compile("^java",Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
        Matcher m = p.matcher(
                "java has regex\nJava has regex\n" +
                        "JAVA has pretty good regular expressions\n" +
                        "Regular expressions are in Java");
        while(m.find())
            System.out.println(m.group());
    }
    /*
    * 由任意数目的非空白符（\\S+）及随后的任意数目的空白符（\\s+）所组成。
    * 目的是捕获每行的最后3个词，每行最后以\$结束。不过，在正常情况下是将\$与整个输入序列的末端相匹配。
    * 所以我们一定要显示地告知正则表达式注意输入序列中的换行符。
    * 这可以由序列开头的模式标记（?m）来完成（模式标记马上就会介绍）。
    *
    *
    * Pattern标记
Pattern类的compile()方法还有另一个版本，它接受一个标记参数，以调整匹配行为：

Pattern Pattern.compile(String regex, int flag)
复制ErrorOK!
其中的flag来自以下Pattern类中的常量

编译标记	效果
Pattern.CANON_EQ	当且仅当两个字符的完全规范分解相匹配时，才认为它们是匹配的。例如，如果我们指定这个标记，表达式\u003F就会匹配字符串?。默认情况下，匹配不考虑规范的等价性
Pattern.CASE_INSENSITIVE(?i)	默认情况下，大小写不敏感的匹配假定只有US-ASCII字符集中的字符才能进行。这个标记允许模式匹配不考虑大小写（大写或小写）。通过指定UNICODE_CASE标记及结合此标记。基于Unicode的大小写不敏感的匹配就可以开启了
Pattern.COMMENTS(?x)	在这种模式下，空格符将被忽略掉，并且以#开始直到行末的注释也会被忽略掉。通过嵌入的标记表达式也可以开启Unix的行模式
Pattern.DOTALL(?s)	在dotall模式下，表达式.匹配所有字符，包括行终止符。默认情况下，.不会匹配行终止符
Pattern.MULTILINE(?m)	在多行模式下，表达式^和$分别匹配一行的开始和结束。^还匹配输入字符串的开始，而$还匹配输入字符串的结尾。默认情况下，这些表达式仅匹配输入的完整字符串的开始和结束
Pattern.UNICODE_CASE(?u)	当指定这个标记，并且开启CASE_INSENSITIVE时，大小写不敏感的匹配将按照与Unicode标准相一致的方式进行。默认情况下，大小写不敏感的匹配假定只能在US-ASCII字符集中的字符才能进行
Pattern.UNIX_LINES(?d)	在这种模式下，在.、^和$的行为中，只识别行终止符\n
在这些标记中，Pattern.CASE_INSENSITIVE、Pattern.MULTILINE以及Pattern.COMMENTS（对声明或文档有用）特别有用。请注意，你可以直接在正则表达式中使用其中的大多数标记，只需要将上表中括号括起来的字符插入到正则表达式中，你希望它起作用的位置即可。

你还可以通过“或”(|)操作符组合多个标记的功能：
    * */
}
