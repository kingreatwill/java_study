import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Test1 {
    @Test
    public void test(){
        BigDecimal bd = new BigDecimal(1);
        BigDecimal f = bd.add(BigDecimal.valueOf(1));
        System.out.println(bd);
        System.out.println(f);
    }
    @Test
    public void test2(){
        long l =1;
        BigInteger bd =BigInteger.valueOf(1);

    }
}
