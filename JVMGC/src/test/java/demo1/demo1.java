package demo1;

import org.junit.Test;

public class demo1 {
    @Test
    public void test(){
        char c = 'x';
        Character ch = c;

        Character ch2 = new Character('x'); //装箱
        char c2 = ch2; //拆箱
        System.out.println(c);
    }
}
