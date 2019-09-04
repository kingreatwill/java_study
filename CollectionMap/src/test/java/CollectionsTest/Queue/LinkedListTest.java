package CollectionsTest.Queue;

import org.junit.Test;

import java.util.LinkedList;

public class LinkedListTest {
    @Test
    public  void test(){
        LinkedList<String> link = new LinkedList<String>();
        //添加元素
        link.addFirst("abc1"); link.addFirst("abc2"); link.addFirst("abc3");
        System.out.println(link);
        // 获取元素
        System.out.println(link.getFirst());
        System.out.println(link.getLast());
        // 删除元素
        System.out.println(link.removeFirst());
        System.out.println(link.removeLast());
        while (!link.isEmpty()) { //判断集合是否为空
            System.out.println(link.pop()); // 移除集合中的栈顶元素
        }
        System.out.println(link);
    }
}
