package com.demo.streamx;

import java.util.stream.Stream;

public class Fibonacci {
    int x = 1;

    Stream<Integer> numbers() {
        return Stream.iterate(0, i -> {
            int result = x + i;
            x = i;
            return result;
        });
    }
// Stream.iterate() 以种子（第一个参数）开头，并将其传给方法（第二个参数）。
// 方法的结果将添加到流，并存储作为第一个参数用于下次调用 iterate()，依次类推。
// 我们可以利用 iterate() 生成一个斐波那契数列
    public static void main(String[] args) {
        new Fibonacci().numbers()
                //.skip(20) // 过滤前 20 个
                .limit(10) // 然后取 10 个
                .forEach(System.out::println);
    }
}
