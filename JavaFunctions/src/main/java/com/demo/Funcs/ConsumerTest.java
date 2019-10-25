package com.demo.Funcs;

import java.util.ArrayList;
import java.util.function.Consumer;

/*
*
* 二 Consumer
1 作用
消费某个对象
2 使用场景
Iterable接口的forEach方法需要传入Consumer，大部分集合类都实现了该接口，用于返回Iterator对象进行迭代。
3 设计思想
开发者调用ArrayList.forEach时，一般希望自定义遍历的消费逻辑，比如：输出日志或者运算处理等。
处理逻辑留给使用者，使用灵活多变。
多变的逻辑能够封装成一个类（实现Consumer接口），将逻辑提取出来。
PASS：Javascript能够将函数传递给另一个函数，这应该算是函数式编程的一个体现，java的function包中的类也是类似的。

public interface Iterable<T> {
    default void forEach(Consumer<? super T> action) {
        Objects.requireNonNull(action);
        for (T t : this) {
            action.accept(t);
        }
    }
}
* */
public class ConsumerTest {

    public static void main(String[] args) {
        ArrayList<Employee> employees = new ArrayList<>();
        String[] prefix = {"A", "B"};
        for (int i = 1; i <= 10; i++)
            employees.add(new Employee(prefix[i % 2] + i, i * 1000));
        employees.forEach(new SalaryConsumer());
        employees.forEach(new NameConsumer());
        employees.forEach(System.out::println);
    }

    static class Employee {
        private String name;
        private int salary;

        public Employee() {
            this.salary = 4000;
        }

        public Employee(String name, int salary) {
            this.name = name;
            this.salary = salary;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSalary() {
            return salary;
        }

        public void setSalary(int salary) {
            this.salary = salary;
        }

        @Override
        public String toString() {
            return new StringBuilder()
                    .append("name:").append(name)
                    .append(",salary:").append(salary)
                    .toString();
        }
    }

    // 输出需要交税的员工
    static class SalaryConsumer implements Consumer<Employee> {

        @Override
        public void accept(Employee employee) {
            if (employee.getSalary() > 2000) {
                System.out.println(employee.getName() + "要交税了.");
            }
        }

    }

    // 输出需要名字前缀是‘A’的员工信息
    static class NameConsumer implements Consumer<Employee> {

        @Override
        public void accept(Employee employee) {
            if (employee.getName().startsWith("A")) {
                System.out.println(employee.getName() + " salary is " + employee.getSalary());
            }
        }

    }

}
