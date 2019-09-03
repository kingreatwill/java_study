package CollectionsTest.List;

import org.junit.Test;

import java.util.*;

/*
* Vector 是同步的，因此开销就比 ArrayList 要大，访问速度更慢。
* 最好使用 ArrayList 而不是 Vector，因为同步操作完全可以由程序员自己来控制；
Vector 每次扩容请求其大小的 2 倍空间，而 ArrayList 是 1.5 倍。
*  替代方案
可以使用 Collections.synchronizedList(); 得到一个线程安全的 ArrayList。

List<String> list = new ArrayList<>();
List<String> synList = Collections.synchronizedList(list);
也可以使用 concurrent 并发包下的 CopyOnWriteArrayList 类。

List<String> list = new CopyOnWriteArrayList<>();
* */
public class VectorTest {
    @Test
    public void test(){
        Vector v1 = new Vector();
        Integer integer1 = 1;
        v1.addElement(integer1);
        v1.addElement("222");
        Enumeration elements = v1.elements();
        while (elements.hasMoreElements()) {
            System.out.println(elements.nextElement());
        }
    }
}
