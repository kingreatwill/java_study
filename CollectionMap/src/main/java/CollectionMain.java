import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

public class CollectionMain {
    public static void main(String... args) {
        // 创建List集合对象
        List<String> list = new ArrayList<String>();
        // 往 尾部添加 指定元素
        list.add("图图");
        list.add("小美");
        list.add("不高兴");
        System.out.println(list);
        // add(int index,String s) 往指定位置添加
        list.add(1, "没头脑");
        System.out.println(list);
        // String remove(int index) 删除指定位置元素 返回被删除元素
        // 删除索引位置为2的元素
        System.out.println("删除索引位置为2的元素");
        System.out.println(list.remove(2));
        System.out.println(list);
        // String set(int index,String s)
        // 在指定位置 进行 元素替代（改）
        // 修改指定位置元素
        list.set(0, "三毛");
        System.out.println(list);
        // String get(int index) 获取指定位置元素
        // 跟size() 方法一起用 来 遍历的
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        //还可以使用增强for
        for (String string : list) {
            System.out.println(string);
        }
    }

    public static void test1(String[] ss){
        test2(ss);
    }
    public static void test2(String... ss){
      /*
      *
      * 可变参数

在JDK1.5之后，如果我们定义一个方法需要接受多个参数，并且多个参数类型一致，我们可以对其简化成如下格 式：

修饰符 返回值类型 方法名(参数类型... 形参名){ }
其实这个书写完全等价与

修饰符 返回值类型 方法名(参数类型[] 形参名){ }
只是后面这种定义，在调用时必须传递数组，而前者可以直接传递数据即可。

JDK1.5以后。出现了简化操作。… 用在参数上，称之为可变参数。

同样是代表数组，但是在调用这个带有可变参数的方法时，不用创建数组(这就是简单之处)，直接将数组中的元素 作为实际参数进行传递，其实编译成的class文件，将这些元素先封装到一个数组中，在进行传递。这些动作都在编 译.class文件时，自动完成了
      * */
    }
}
