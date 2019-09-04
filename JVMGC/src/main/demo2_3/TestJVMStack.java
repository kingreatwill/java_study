import org.junit.Test;

/**
 * 递归调用函数打印出调用次数
 */
public class TestJVMStack {
    //1.声明一个计数变量，统计调用多少次
    private int count=0;

    //2.声明一个递归函数
    public void recursion(){
        count++;
        recursion();
    }
    //3.测试递归函数调用次数
    @Test
    public void testJvmStack(){
        try {
            recursion();
        } catch (Error e) {
            System.out.println(count);
            e.printStackTrace();
        }
    }
}
