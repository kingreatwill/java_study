package common;

import org.junit.Test;

public class String8Test {
    @Test
    public void test4(){
        //好像有个小问题， Java8 里String s = new String("A"+"B"); 不会创建“A” 和 “B”对象，
        // Java编译器会把字符串字面量的相加翻译成"AB"。可以用下面的代码验证：
        String s = new String("AB" + "OP");  //----- 1  true
        //String s = new String("OP");           //     ----- 2 false
        String s1 = new StringBuilder().append("O").append("P").toString();
        System.out.println(s1.intern()==s1);
    }
}
