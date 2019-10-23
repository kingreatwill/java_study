import org.junit.jupiter.api.Test;

public class Test2 {
    class DataOnly {
        int i;
        double d;
        boolean b;
    }
    @Test
    public void test1(){
        int x;
        //System.out.println(x);
        /*
        这里的变量 x 不会自动初始化为0，
        因而在使用变量 x 之前，程序员有责任主动地为其赋值（和 C 、C++ 一致）。如果我们忘记了这一步， Java 将会提示我们“编译时错误，该变量可能尚未被初始化”。
         这一点做的比 C++ 更好，在后者中，编译器只是提示警告，而在 Java 中则直接报错。
        * */
        DataOnly data = new DataOnly();
        System.out.println(data.b);
        data.b=true;
        System.out.println(data.b);
    }
}
