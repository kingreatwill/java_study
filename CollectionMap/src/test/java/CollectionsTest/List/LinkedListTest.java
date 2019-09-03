package CollectionsTest.List;

import org.junit.Test;

import java.util.LinkedList;

/*
1. 概览
基于双向链表实现，使用 Node 存储链表节点信息。

private static class Node<E> {
    E item;
    Node<E> next;
    Node<E> prev;
}
每个链表存储了 first 和 last 指针：

transient Node<E> first;
transient Node<E> last;



2. 与 ArrayList 的比较
ArrayList 基于动态数组实现，LinkedList 基于双向链表实现；
ArrayList 支持随机访问，LinkedList 不支持；
LinkedList 在任意位置添加删除元素更快。
* */
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
