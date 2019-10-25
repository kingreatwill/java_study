import org.junit.jupiter.api.Test;

// https://segmentfault.com/a/1190000009888357?utm_source=tag-newest
public class StringInfo {
    // String是引用类型
    // String对象是不可变的
    // 字符串常量池
    //字符串常量池的设计思想
    //字符串的分配，和其他的对象分配一样，耗费高昂的时间与空间代价，作为最基础的数据类型，大量频繁的创建字符串，极大程度地影响程序的性能
    //
    //JVM为了提高性能和减少内存开销，在实例化字符串常量的时候进行了一些优化
    //
    //为字符串开辟一个字符串常量池，类似于缓存区
    //
    //创建字符串常量时，首先坚持字符串常量池是否存在该字符串
    //
    //存在该字符串，返回引用实例，不存在，实例化该字符串并放入池中
    //
    //实现的基础
    //
    //实现该优化的基础是因为字符串是不可变的，可以不用担心数据冲突进行共享
    //
    //运行时实例创建的全局字符串常量池中有一个表，总是为池中每个唯一的字符串对象维护一个引用,这就意味着它们一直引用着字符串常量池中的对象，所以，在常量池中的这些字符串不会被垃圾收集器回收

    @Test
    public void test1(){
        String s1 = "Ab@1 5hJ";
        String s2 = s1;
        s1 = "456";
        System.out.printf("s1: %s ,s2: %s",s1,s2);
        // s1: 456 ,s2: Ab@1 5hJ
        //如果string是引用类型，按理Str1和Str指针都指向同一内存地址，如果Str的内容发生改变，Str1应该也会相应变化。
        //此例子，看着string更像是值类型。
    }

    @Test
    public void test2(){
        String str1 = "hello";
        String str2 = new String("hello");
        System.out.printf("str1.equals(str2): %b\n",str1.equals(str2)); //true
        System.out.printf("str1 == str2: %b\n",str1 == str2); //false
    }

    @Test
    public void test3(){
        String str1 = "hello";
        String str2 = "hello";
        System.out.printf("str1.equals(str2): %b\n",str1.equals(str2)); //true
        System.out.printf("str1 == str2: %b\n",str1 == str2); //true
    }
    /*
  1问：  String str4 = new String(“abc”) 创建多少个对象？
在常量池中查找是否有“abc”对象
有则返回对应的引用实例
没有则创建对应的实例对象
在堆中 new 一个 String("abc") 对象
将对象地址赋值给str4,创建一个引用
所以，常量池中没有“abc”字面量则创建两个对象，否则创建一个对象，以及创建一个引用

再问：
String str1 = new String("A"+"B") ; 会创建多少个对象?
String str2 = new String("ABC") + "ABC" ; 会创建多少个对象?
str1：
字符串常量池："A","B","AB" : 3个
堆：new String("AB") ：1个
引用： str1 ：1个
总共 ： 5个
str2 ：
字符串常量池："ABC" : 1个
堆：new String("ABC") ：1个
引用： str2 ：1个
总共 ： 3个
    * */
    @Test
    public void test4(){
        //好像有个小问题， Java8 里String s = new String("A"+"B"); 不会创建“A” 和 “B”对象，
        // Java编译器会把字符串字面量的相加翻译成"AB"。可以用下面的代码验证：
        String s = new String("AB" + "OP");  //----- 1 java8-true   java13 false
       // String s = new String("OP");           //     ----- 2 java8-false
        String s1 = new StringBuilder().append("O").append("P").toString();
        System.out.println(s1.intern()==s1);
    }

    @Test
    public void test5(){
        // Create three strings in three different ways.
        String s1 = "Hello";
        String s2 = new StringBuffer("He").append("llo").toString();
        String s3 = s2.intern();

        // Determine which strings are equivalent using the ==
        // operator
        System.out.println("s1 == s2? " + (s1 == s2)); // false
        System.out.println("s1 == s3? " + (s1 == s3)); // true
        // 通过new操作符创建的字符串对象不指向字符串池中的任何对象，但是可以通过使用字符串的intern()方法来指向其中的某一个。
        // java.lang.String.intern()返回一个保留池字符串，就是一个在全局字符串池中有了一个入口。如果以前没有在全局字符串池中，那么它就会被添加到里面
    }
}
