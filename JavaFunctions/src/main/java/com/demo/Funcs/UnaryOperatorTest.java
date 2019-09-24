package com.demo.Funcs;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

/*
* 1 作用
UnaryOperator继承了Function，与Function作用相同
不过UnaryOperator，限定了传入类型和返回类型必需相同
2 使用场景
List.replaceAll(UnaryOperator) // 该列表的所有元素替换为运算结算元素
Stream.iterate(T,UnaryOperator) // 重复对seed调用UnaryOperator来生成元素
3 设计思想
一元函数的思想，将同类转换逻辑提取出来，解耦合
* */
public class UnaryOperatorTest{
    public static void main(String[] args) {
        ArrayList<Employee> employees = new ArrayList<>();
        String[] prefix = {"B", "A"};
        for (int i = 1; i <= 10; i++)
            employees.add(new Employee(prefix[i % 2] + i, i * 1000));
        System.out.println("公司进行薪资调整...");
        salaryAdjustment(employees,new SalaryAdjustment(4000));
        employees.forEach(System.out::println);
    }

    static void salaryAdjustment(List<Employee> list, UnaryOperator<Employee> operator) {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, operator.apply(list.get(i)));
        }
    }

    static class SalaryAdjustment implements UnaryOperator<Employee> {
        private int salary;

        public SalaryAdjustment(int salary) {
            this.salary = salary;
        }

        @Override
        public Employee apply(Employee employee) {
            employee.setSalary(salary);
            return employee;
        }

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
