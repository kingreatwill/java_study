# 一 、List接口特点：

1.它是一个元素存取有序的集合。例如，存元素的顺序是11、22、33。那么集合中，元素的存储就是按照11、

22、33的顺序完成的）。

2.它是一个带有索引的集合，通过索引就可以精确的操作集合中的元素（与数组的索引是一个道理）。

3.集合中可以有重复的元素，通过元素的equals方法，来比较是否为重复的元素。

# 二 、List接口中常用方法

List作为Collection集合的子接口，不但继承了Collection接口中的全部方法，而且还增加了一些根据元素索引来操作集合的特有方法，如下：

public void add(int index, E element) : 将指定的元素，添加到该集合中的指定位置上。

public E get(int index) :返回集合中指定位置的元素。

public E remove(int index) : 移除列表中指定位置的元素, 返回的是被移除的元素。

public E set(int index, E element) :用指定元素替换集合中指定位置的元素,返回值的更新前的元素。

# xxx
Collection 继承了 Iterable 接口，其中的 iterator() 方法能够产生一个 Iterator 对象，通过这个对象就可以迭代遍历 Collection 中的元素。

从 JDK 1.5 之后可以使用 foreach 方法来遍历实现了 Iterable 接口的聚合对象。

List<String> list = new ArrayList<>();
list.add("a");
list.add("b");
for (String item : list) {
    System.out.println(item);
}

## 适配器模式
java.util.Arrays#asList() 可以把数组类型转换为 List 类型。

@SafeVarargs
public static <T> List<T> asList(T... a)
应该注意的是 asList() 的参数为泛型的变长参数，不能使用基本类型数组作为参数，只能使用相应的包装类型数组。

Integer[] arr = {1, 2, 3};
List list = Arrays.asList(arr);
也可以使用以下方式调用 asList()：

List list = Arrays.asList(1, 2, 3);