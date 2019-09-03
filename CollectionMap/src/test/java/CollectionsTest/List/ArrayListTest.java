package CollectionsTest.List;

import common.Student;
import org.junit.Test;

import java.util.*;

public class ArrayListTest {
/*
1 概览
 ArrayList 是基于数组实现的，所以支持快速随机访问。RandomAccess 接口标识着该类支持快速随机访问。
public class ArrayList<E> extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable
数组的默认大小为 10。
private static final int DEFAULT_CAPACITY = 10;
2 扩容
添加元素时使用 ensureCapacityInternal() 方法来保证容量足够，如果不够时，需要使用 grow() 方法进行扩容，新容量的大小为 oldCapacity + (oldCapacity >> 1)，也就是旧容量的 1.5 倍。

扩容操作需要调用 Arrays.copyOf() 把原数组整个复制到新数组中，这个操作代价很高，因此最好在创建 ArrayList 对象时就指定大概的容量大小，减少扩容操作的次数。

public boolean add(E e) {
    ensureCapacityInternal(size + 1);  // Increments modCount!!
    elementData[size++] = e;
    return true;
}

private void ensureCapacityInternal(int minCapacity) {
    if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
        minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
    }
    ensureExplicitCapacity(minCapacity);
}

private void ensureExplicitCapacity(int minCapacity) {
    modCount++;
    // overflow-conscious code
    if (minCapacity - elementData.length > 0)
        grow(minCapacity);
}

private void grow(int minCapacity) {
    // overflow-conscious code
    int oldCapacity = elementData.length;
    int newCapacity = oldCapacity + (oldCapacity >> 1);
    if (newCapacity - minCapacity < 0)
        newCapacity = minCapacity;
    if (newCapacity - MAX_ARRAY_SIZE > 0)
        newCapacity = hugeCapacity(minCapacity);
    // minCapacity is usually close to size, so this is a win:
    elementData = Arrays.copyOf(elementData, newCapacity);
}

3 删除元素
需要调用 System.arraycopy() 将 index+1 后面的元素都复制到 index 位置上，该操作的时间复杂度为 O(N)，可以看出 ArrayList 删除元素的代价是非常高的。

public E remove(int index) {
    rangeCheck(index);
    modCount++;
    E oldValue = elementData(index);
    int numMoved = size - index - 1;
    if (numMoved > 0)
        System.arraycopy(elementData, index+1, elementData, index, numMoved);
    elementData[--size] = null; // clear to let GC do its work
    return oldValue;
}

4. Fail-Fast
modCount 用来记录 ArrayList 结构发生变化的次数。结构发生变化是指添加或者删除至少一个元素的所有操作，或者是调整内部数组的大小，仅仅只是设置元素的值不算结构发生变化。

在进行序列化或者迭代等操作时，需要比较操作前后 modCount 是否改变，如果改变了需要抛出 ConcurrentModificationException。

private void writeObject(java.io.ObjectOutputStream s)
    throws java.io.IOException{
    // Write out element count, and any hidden stuff
    int expectedModCount = modCount;
    s.defaultWriteObject();

    // Write out size as capacity for behavioural compatibility with clone()
    s.writeInt(size);

    // Write out all elements in the proper order.
    for (int i=0; i<size; i++) {
        s.writeObject(elementData[i]);
    }

    if (modCount != expectedModCount) {
        throw new ConcurrentModificationException();
    }
}

5. 序列化
ArrayList 基于数组实现，并且具有动态扩容特性，因此保存元素的数组不一定都会被使用，那么就没必要全部进行序列化。

保存元素的数组 elementData 使用 transient 修饰，该关键字声明数组默认不会被序列化。

transient Object[] elementData; // non-private to simplify nested class access
ArrayList 实现了 writeObject() 和 readObject() 来控制只序列化数组中有元素填充那部分内容。

private void readObject(java.io.ObjectInputStream s)
    throws java.io.IOException, ClassNotFoundException {
    elementData = EMPTY_ELEMENTDATA;

    // Read in size, and any hidden stuff
    s.defaultReadObject();

    // Read in capacity
    s.readInt(); // ignored

    if (size > 0) {
        // be like clone(), allocate array based upon size not capacity
        ensureCapacityInternal(size);

        Object[] a = elementData;
        // Read in all elements in the proper order.
        for (int i=0; i<size; i++) {
            a[i] = s.readObject();
        }
    }
}
private void writeObject(java.io.ObjectOutputStream s)
    throws java.io.IOException{
    // Write out element count, and any hidden stuff
    int expectedModCount = modCount;
    s.defaultWriteObject();

    // Write out size as capacity for behavioural compatibility with clone()
    s.writeInt(size);

    // Write out all elements in the proper order.
    for (int i=0; i<size; i++) {
        s.writeObject(elementData[i]);
    }

    if (modCount != expectedModCount) {
        throw new ConcurrentModificationException();
    }
}
序列化时需要使用 ObjectOutputStream 的 writeObject() 将对象转换为字节流并输出。而 writeObject() 方法在传入的对象存在 writeObject() 的时候会去反射调用该对象的 writeObject() 来实现序列化。反序列化使用的是 ObjectInputStream 的 readObject() 方法，原理类似。

ArrayList list = new ArrayList();
ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
oos.writeObject(list);
 */

    /*
    基于动态数组实现，支持随机访问。
    线程不安全的
    允许出现重复的元素
    有序
    元素增删慢，查找快
     */
    @Test
    public void test(){
        List<String> list = new ArrayList<String>(20);
        list.add("一");
        list.add("二");
        list.add("三");
        list.add("四");
        list.add("五");

        list.forEach(a->System.out.println(a));

        System.out.println("----------------------------------");
        list.add(1,"1");
        list.forEach(System.out::println);
        System.out.println("----------------------------------");
        System.out.println(list.contains("1"));
        list.add(1,"1");
        list.remove("1");
        list.remove("1");
        list.remove("1");
        list.set(1,"132");
        for(int i = 0;i<list.size();i++){
            System.out.println(list.get(i));
        }
        System.out.println("----------------------------------");
        System.out.println(list.indexOf("132"));
        for (String a : list) {
            System.out.println(a);
        }

        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.charAt(0) - o1.charAt(0);
            }
        });
        System.out.println("----------------------------------");
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("----------------------------------");
        String[] array = new String[list.size()];
        list.toArray(array);

        List<Integer> list2 = Arrays.asList(1, 2, 3);
        Integer[] arr = {1, 2, 3};
        List<Integer> list3 = Arrays.asList(arr);
        System.out.println("----------------------------------");

        System.out.println(list2.getClass());
        System.out.println(list.getClass());
    }

    @Test
    public void stream(){
        Integer[] arr = {1, 2, 3};
        List<Integer> list = new ArrayList<>(Arrays.asList(arr));
        System.out.println(list.stream().filter(a->a==2).findAny().get());
    }

    @Test
    public void asList(){
        {
            int[] ints = {1, 2, 3, 4, 5};
            List list = Arrays.asList(ints);
            System.out.println(list.size());
            System.out.println(list.get(0));
            System.out.println("list 的类型:" + list.get(0).getClass());
            System.out.println("list.get(0) == ints：" + list.get(0).equals(ints));
        }
        System.out.println("------------------------");
        {
            Integer[] ints = {1, 2, 3, 4, 5};
            List list = Arrays.asList(ints);
            System.out.println(list.size());
            System.out.println("list.get(0) 的类型:" + list.get(0).getClass());
            System.out.println("list.get(0) == ints[0]：" + list.get(0).equals(ints[0]));
        }
        System.out.println("在使用 asList 时不要将基本数据类型当做参数。");
        {
            Integer[] ints = {1,2,3,4,5};
            List list = Arrays.asList(ints); // 返回的
            System.out.println(list.getClass());
            System.out.println(new ArrayList<Integer>().getClass());
            //list.add(6); // asList 产生的列表不可操作
        }
    }



    @Test
    public void collection(){
        Integer[] arr = {2, 1, 3};
        List<Integer> list = new ArrayList<>(Arrays.asList(arr));
        System.out.println(list);
        Collections.sort(list);
        System.out.println(list);

        Collections.addAll(list, 5, 222, -1,4);
        System.out.println(list);
        Collections.sort(list);
        System.out.println(list);

        ArrayList<String> list2 = new ArrayList<String>();
        list2.add("cba");
        list2.add("aba");
        list2.add("sba");
        list2.add("nba");
        System.out.println(list2);
        //排序方法 按照第一个单词的降序
        Collections.sort(list2, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.charAt(0) - o1.charAt(0);
            }
        });
        System.out.println(list2);

        ArrayList<Student> list3 = new ArrayList<Student>();
        list3.add(new Student("rose",18));
        list3.add(new Student("jack",16));
        list3.add(new Student("abc",16));
        list3.add(new Student("ace",17));
        list3.add(new Student("mark",16));
        Collections.sort(list3,new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o2.getAge() - o1.getAge();
            }
        });

        /*
        想要直接使用Collections.sort(list3) 就需要实现Comparable接口
        * public class Student implements Comparable<Student>{
....
@Override
public int compareTo(Student o) {
return this.age‐o.age;//升序
   //return 0;                //当compareTo方法返回0的时候集合中只有一个元素
       // return 1;                //当compareTo方法返回正数的时候集合会怎么存就怎么取
        //return -1;                //当compareTo方法返回负数的时候集合会倒序存储
}
}
        * */
    }
}
