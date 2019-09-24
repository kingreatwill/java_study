package com.demo.Funcs;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/*
* 1 作用
实现一个”一元函数“，即传入一个值经过函数的计算返回另一个值。
2 使用场景
V HashMap.computeIfAbsent(K , Function<K, V>) // 简化代码，如果指定的键尚未与值关联或与null关联，使用函数返回值替换。
<R> Stream<R> map(Function<? super T, ? extends R> mapper); // 转换流
3 设计思想
一元函数的思想，将转换逻辑提取出来，解耦合
* */
public class FunctionTest {
    public static void main(String[] args) {
        ArrayList<Employee> employees = new ArrayList<>();
        String[] prefix = {"B", "A"};
        for (int i = 1; i <= 10; i++)
            employees.add(new Employee(prefix[i % 2] + i, i * 1000));
        int[] expenses = ListToArray(employees, new EmployeeToExpenses());// 公司对单个员工的支出数组
        int[] incomes = ListToArray(employees, new EmployeeToIncome()); // 单个员工的收入数组
        System.out.println("社保+公积金+税=" + (sum(expenses) - sum(incomes)) + "元");
    }

    private static int[] ListToArray(List<Employee> list, Function<Employee, Integer> function) {
        int[] ints = new int[list.size()];
        for (int i = 0; i < ints.length; i++)
            ints[i] = function.apply(list.get(i));
        return ints;
    }

    private static int sum(int[] salarys) {
        int sum = 0;
        for (int i = 0; i < salarys.length; i++)
            sum += salarys[i];
        return sum;
    }

    // 公司支出
    static class EmployeeToExpenses implements Function<Employee, Integer> {

        @Override
        public Integer apply(Employee employee) {
            // 假设公司公积金和社保为工资的20%
            return Double.valueOf(employee.getSalary() * (1 + 0.2)).intValue();
        }

    }

    // 员工实际到手工资
    static class EmployeeToIncome implements Function<Employee, Integer> {

        @Override
        public Integer apply(Employee employee) {
            // 假设员工薪水 * 80% 为到手工资
            return Double.valueOf(employee.getSalary() * (1 - 0.2)).intValue();
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
