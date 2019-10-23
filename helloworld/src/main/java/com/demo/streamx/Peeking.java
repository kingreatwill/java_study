package com.demo.streamx;

public class Peeking {
    public static void main(String[] args) throws Exception {
        new FileToWordsBuilder("helloworld/src/main/resources/Cheese.dat").stream()
                .skip(1)
                .limit(4)
                .map(w -> w + " ")
                .peek(System.out::println)
                .map(String::toUpperCase)
                .peek(System.out::println)
                .map(String::toLowerCase)
                .forEach(System.out::println);
    }
}
