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