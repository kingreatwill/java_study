import org.junit.Test;

public class Test3 {
    @Test
    public void test1(){
        System.getProperties().list(System.out);
        System.out.println(System.getProperty("user.name"));
        System.out.println(System.getProperty("java.library.path"));
    }
}
