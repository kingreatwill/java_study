package com.demo.Funcs;

import java.util.function.Predicate;
import java.util.stream.Stream;

public class PredicateComposition {
    static Predicate<String>
            p1 = s -> s.contains("bar"),
            p2 = s -> s.length() < 5,
            p3 = s -> s.contains("foo"),
            p4 = p1.negate().and(p2).or(p3);
    public static void main(String[] args) {
        Stream.of("bar", "foobar", "foobaz", "fongopuckey", "fon")
                .filter(p4)
                .forEach(System.out::println);
    }

    // p4 获取到了所有断言并组合成一个更复杂的断言。解读：如果字符串中不包含 bar 且长度小于 5，或者它包含 foo ，则结果为 true


}
