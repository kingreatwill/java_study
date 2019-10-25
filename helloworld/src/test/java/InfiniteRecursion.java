import java.util.stream.Stream;

public class InfiniteRecursion {
    // 意外递归
    @Override
    public String toString() {
        return " InfiniteRecursion address: " + super.toString() + "\n";
        //return " InfiniteRecursion address: " + this.toString() + "\n";// 死循环
       // return " InfiniteRecursion address: " + this + "\n";// 死循环
    }
    public static void main(String[] args) {
        Stream.generate(InfiniteRecursion::new)
                .limit(10)
                .forEach(System.out::println);
    }
}
//由InfiniteRecursion类型转换为String类型。
// 因为编译器发现一个String对象后面跟着一个“+”，而“+”后面的对象不是String，
// 于是编译器试着将this转换成一个String。它怎么转换呢？正是通过调用this上的toString()方法，于是就发生了递归调用。
//
//如果你真的想要打印对象的内存地址，应该调用Object.toString()方法，这才是负责此任务的方法。所以，不要使用this，而是应该调用super.toString()方法。