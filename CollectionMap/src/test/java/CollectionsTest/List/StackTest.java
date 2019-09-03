package CollectionsTest.List;

import org.junit.Test;

import java.util.Stack;

public class StackTest {

    @Test
    public void  test(){
        Stack<Integer> st = new Stack<Integer>();
        st.push(1);//把项压入堆栈顶部。
        st.push(5);//把项压入堆栈顶部。
        System.out.println(st);
        System.out.println(st.search(1));//返回对象在堆栈中的位置，以 1 为基数。
        System.out.println(st.pop());//移除堆栈顶部的对象，并作为此函数的值返回该对象。
        System.out.println(st);
        System.out.println(st.peek());//查看堆栈顶部的对象，但不从堆栈中移除它。
        System.out.println(st);
        System.out.println(st.empty());//测试堆栈是否为空。
    }
}
