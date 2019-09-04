package MapsTest;

import org.junit.Test;

import java.util.TreeMap;

public class TreeMapTest {
    @Test
    public void test1(){
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(3, "val3");
        treeMap.put(2, "val2");
        treeMap.put(1, "val1");
        treeMap.put(5, "val5");
        treeMap.put(4, "val4");
        treeMap.remove(2);
        treeMap.replace(1,"val111");
        System.out.println(treeMap);

        //返回指定的Key大于或等于的最小值的元素，如果没有，则返回null
        System.out.println(treeMap.ceilingEntry(2));

    }
}
