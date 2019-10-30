package com.demo.shuzu;

import java.util.Arrays;

public class ArrayOfGenericType<T> {
    T[] array; // OK
    @SuppressWarnings("unchecked")
    public ArrayOfGenericType(int size) {
        // error: generic array creation:
        //- array = new T[size];
        array = (T[])new Object[size]; // unchecked cast
    }

    @Override
    public String toString(){
       return Arrays.toString(array);
    }
    // error: generic array creation:
    //- public <U> U[] makeArray() { return new U[10]; }

    public static void main(String[] args) {
        ArrayOfGenericType<Integer> f = new ArrayOfGenericType<Integer>(10);
        System.out.println(f);
    }
}
