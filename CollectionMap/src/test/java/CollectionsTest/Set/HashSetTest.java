package CollectionsTest.Set;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class HashSetTest {

    /*
    java.util.HashSet 是 Set 接口的一个实现类，它所存储的元素是不可重复的，
    并且元素都是无序的(即存取顺序 不一致)。
    java.util.HashSet 底层的实现其实是一个 java.util.HashMap

    HashSet 是根据对象的哈希值来确定元素在集合中的存储位置，因此具有良好的存取和查找性能。
    保证元素唯一性 的方式依赖于： hashCode 与 equals 方法。

    给HashSet中存放自定义类型元素时，需要重写对象中的hashCode和equals方法，
    建立自己的比较方式，才能保 证HashSet集合中的对象唯一
    * */
    @Test
    public void test(){
        //创建 Set集合
        Set<String> set = new HashSet<String>();
        //添加元素
        set.add(new String("cba"));
        set.add("abc");
        set.add("bac");
        set.add("cba");
        set.add("222");
        set.add("111");
        //遍历
        for (String name : set) {
            System.out.println(name);
        }
    }
}
