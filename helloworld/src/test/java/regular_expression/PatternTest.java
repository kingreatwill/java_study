package regular_expression;

public class PatternTest {
    // java.util.regex包中的Pattern类
    /*
表达式	含义
B	    指定字符B
\xhh	十六进制值为0xhh的字符
\\uhhhh	十六进制表现为0xhhhh的Unicode字符
\t	    制表符Tab
\n	    换行符
\r	    回车
\f	    换页
\e	    转义（Escape）


表达式	        含义
.	            任意字符
[abc]	        包含a、b或c的任何字符（和`a
[^abc]	        除a、b和c之外的任何字符（否定）
[a-zA-Z]	    从a到z或从A到Z的任何字符（范围）
[abc[hij]]	    a、b、c、h、i、j中的任意字符（与`a
[a-z&&[hij]]	任意h、i或j（交）
\s	            空白符（空格、tab、换行、换页、回车）
\S	            非空白符（[^\s]）
\d	            数字（[0-9]）
\D	            非数字（[^0-9]）
\w	            词字符（[a-zA-Z_0-9]）
\W	            非词字符（[^\w]）


逻辑操作符	含义
XY	        Y跟在X后面
X|Y	        X或Y
(X)	        捕获组（capturing group）。可以在表达式中用\i引用第i个捕获组

边界匹配符	含义
^	        一行的开始
$	        一行的结束
\b	        词的边界
\B	        非词的边界
\G	        前一个匹配的结束
     */
    public static void main(String[] args) {
        for(String pattern : new String[]{
                "Rudolph",
                "[rR]udolph",
                "[rR][aeiou][a-z]ol.*",
                "R.*" })
            System.out.println("Rudolph".matches(pattern));
    }
}
