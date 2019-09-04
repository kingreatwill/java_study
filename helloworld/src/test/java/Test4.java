import org.junit.Test;

import java.util.Random;

public class Test4 {
    @Test
    public void test1(){
        System.out.println((1 / 32));
        Random rand = new Random(47);
        System.out.println(rand.nextInt(100));
        System.out.println(rand.nextInt(100));
        System.out.println(rand.nextInt(100));
        System.out.println(rand.nextInt(100));
    }
}
