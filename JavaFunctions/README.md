下表描述了 `java.util.function`函数式接口 中的目标类型：

| **特征** |**函数式方法名**|**示例**|
| :---- | :----: | :----: |
|无参数； <br> 无返回值|**Runnable** <br> (java.lang)  <br>  `run()`|**Runnable**|
|无参数； <br> 返回类型任意|**Supplier** <br> `get()` <br> `getAs类型()`| **Supplier`<T>`  <br> BooleanSupplier  <br> IntSupplier  <br> LongSupplier  <br> DoubleSupplier**|
|无参数； <br> 返回类型任意|**Callable** <br> (java.util.concurrent)  <br> `call()`|**Callable`<V>`**|
|1 参数； <br> 无返回值|**Consumer** <br> `accept()`|**`Consumer<T>` <br> IntConsumer <br> LongConsumer <br> DoubleConsumer**|
|2 参数 **Consumer**|**BiConsumer** <br> `accept()`|**`BiConsumer<T,U>`**|
|2 参数 **Consumer**； <br> 1 引用； <br> 1 基本类型|**Obj类型Consumer** <br> `accept()`|**`ObjIntConsumer<T>` <br> `ObjLongConsumer<T>` <br> `ObjDoubleConsumer<T>`**|
|1 参数； <br> 返回类型不同|**Function** <br> `apply()` <br> **To类型** 和 **类型To类型** <br> `applyAs类型()`|**Function`<T,R>` <br> IntFunction`<R>` <br> `LongFunction<R>` <br> DoubleFunction`<R>` <br> ToIntFunction`<T>` <br> `ToLongFunction<T>` <br> `ToDoubleFunction<T>` <br> IntToLongFunction <br> IntToDoubleFunction <br> LongToIntFunction <br> LongToDoubleFunction <br> DoubleToIntFunction <br> DoubleToLongFunction**|
|1 参数； <br> 返回类型相同|**UnaryOperator** <br> `apply()`|**`UnaryOperator<T>` <br> IntUnaryOperator <br> LongUnaryOperator <br> DoubleUnaryOperator**|
|2 参数类型相同； <br> 返回类型相同|**BinaryOperator** <br> `apply()`|**`BinaryOperator<T>` <br> IntBinaryOperator <br> LongBinaryOperator <br> DoubleBinaryOperator**|
|2 参数类型相同; <br> 返回整型|Comparator <br> (java.util) <br> `compare()`|**`Comparator<T>`**|
|2 参数； <br> 返回布尔型|**Predicate** <br> `test()`|**`Predicate<T>` <br> `BiPredicate<T,U>` <br> IntPredicate <br> LongPredicate <br> DoublePredicate**|
|参数基本类型； <br> 返回基本类型|**类型To类型Function** <br> `applyAs类型()`|**IntToLongFunction <br> IntToDoubleFunction <br> LongToIntFunction <br> LongToDoubleFunction <br> DoubleToIntFunction <br> DoubleToLongFunction**|
|2 参数类型不同|**Bi操作** <br> (不同方法名)|**`BiFunction<T,U,R>` <br> `BiConsumer<T,U>` <br> `BiPredicate<T,U>` <br> `ToIntBiFunction<T,U>` <br> `ToLongBiFunction<T,U>` <br> `ToDoubleBiFunction<T>`**|


## 函数组合


函数组合（Function Composition）意为“多个函数组合成新函数”。它通常是函数式编程的基本组成部分。在前面的 `TransformFunction.java` 类中，有一个使用 `andThen()` 的函数组合示例。一些 `java.util.function` 接口中包含支持函数组合的方法 [^7]。

| 组合方法 | 支持接口 |
| :----- | :----- |
| `andThen(argument)` <br> 根据参数执行原始操作 | **Function <br> BiFunction <br> Consumer <br> BiConsumer <br> IntConsumer <br> LongConsumer <br> DoubleConsumer <br> UnaryOperator <br> IntUnaryOperator <br> LongUnaryOperator <br> DoubleUnaryOperator <br> BinaryOperator** |
| `compose(argument)` <br> 根据参数执行原始操作 | **Function <br> UnaryOperator <br> IntUnaryOperator <br> LongUnaryOperator <br> DoubleUnaryOperator** |
| `and(argument)`  <br> 短路**逻辑与**原始断言和参数断言 | **Predicate <br> BiPredicate <br> IntPredicate <br> LongPredicate <br> DoublePredicate** |
| `or(argument)` <br> 短路**逻辑或**原始断言和参数断言 | **Predicate <br> BiPredicate <br> IntPredicate <br> LongPredicate <br> DoublePredicate** |
| `negate()` <br> 该断言的**逻辑否**断言| **Predicate <br> BiPredicate <br> IntPredicate <br> LongPredicate <br> DoublePredicate** |


下例使用了 `Function` 里的 `compose()`和 `andThen()`。代码示例：

```java
// functional/FunctionComposition.java

import java.util.function.*;

public class FunctionComposition {
  static Function<String, String>
    f1 = s -> {
      System.out.println(s);
      return s.replace('A', '_');
    },
    f2 = s -> s.substring(3),
    f3 = s -> s.toLowerCase(),
    f4 = f1.compose(f2).andThen(f3);
  public static void main(String[] args) {
    System.out.println(
      f4.apply("GO AFTER ALL AMBULANCES"));
  }
}
```

输出结果：

```
AFTER ALL AMBULANCES
_fter _ll _mbul_nces
```