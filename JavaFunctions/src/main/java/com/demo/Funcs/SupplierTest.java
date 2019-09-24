package com.demo.Funcs;

import java.util.function.Supplier;

/*
* 1 作用
创建一个对象（工厂类）
2 使用场景
Optional.orElseGet(Supplier<? extends T>)：当this对象为null，就通过传入supplier创建一个T返回。
3 设计思想
封装工厂创建对象的逻辑
* */
public class SupplierTest {
    public static void main(String[] args) {
        // 生成固定工资的员工
        Supplier<Employee> supplier = () -> new Employee();
        Employee employee1 = supplier.get();
        employee1.setName("test1");
        Employee employee2 = supplier.get();
        employee2.setName("test2");
        System.out.println("employee1:" + employee1);
        System.out.println("employee2:" + employee2);
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
}
