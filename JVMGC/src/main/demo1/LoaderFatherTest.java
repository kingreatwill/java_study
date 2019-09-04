
/**
 * 测试：子类调用父类静态成员，是否对子类进行加载和初始化？
 */
public class LoaderFatherTest {
    public static void main(String[] args) {
        System.out.println("Son.myname = " + Son.myname);
        Son son = new Son();
        System.out.println("Son.myname = " + son.myname);
    }

}
