package CollectionsTest.Set;

import common.Student;
import org.junit.Test;

import java.util.TreeSet;

public class TreeSetTest {

    /*
    * 必须实现 java.lang.Comparable
    * 1、不能有重复的元素；
2、具有排序功能；
3、TreeSet中的元素必须实现Comparable接口并重写compareTo()方法，TreeSet判断元素是否重复 、以及确定元素的顺序 靠的都是这个方法；
①对于Java类库中定义的类，TreeSet可以直接对其进行存储，如String，Integer等,因为这些类已经实现了Comparable接口);
②对于自定义类，如果不做适当的处理，TreeSet中只能存储一个该类型的对象实例，否则无法判断是否重复。
4、依赖TreeMap。
5、相对HashSet,TreeSet的优势是有序，劣势是相对读取慢。根据不同的场景选择不同的集合。
    * */
    @Test
    public void test(){
        TreeSet<Student> ts = new TreeSet<>();
        ts.add(new Student("张三", 11));
        ts.add(new Student("赵六", 21));
        ts.add(new Student("李四", 12));
        ts.add(new Student("李四", 12));
        ts.add(new Student("王五", 15));
        for (Student t : ts ){
            System.out.println(t);
        }

    }
}
