package com.demo.Funcs;


/*
*
*三 Predicate
1 作用
判断对象是否符合某个条件
2 使用场景
​ ArrayList的removeIf(Predicate)：删除符合条件的元素

​ 如果条件硬编码在ArrayList中，它将提供无数的实现，但是如果让调用者传入条件，这样ArrayList就可以从复杂和无法猜测的业务中解放出来。

3 设计思想
提取条件，让条件从处理逻辑脱离出来，解耦合
4 DEMO
// employee.getSalary() > 2000 提取成一个条件类
class SalaryConsumer implements Consumer<Employee> {
     @Override
     public void accept(Employee employee) {
         // 自行传入本地的最低交税工资
         if (new SalaryPredicate(2000).test(employee)) {
             System.out.println(employee.getName() + "要交税了.");
         }
     }
 }

class SalaryPredicate implements  Predicate<Employee>{
    private int tax;

    public SalaryPredicate(int tax) {
        this.tax = tax;
    }

    @Override
    public boolean test(Employee employee) {
        return employee.getSalary() > tax;
    }
}
*
**/
public class PredicateTest {
}
