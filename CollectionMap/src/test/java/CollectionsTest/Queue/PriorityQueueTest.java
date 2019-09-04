package CollectionsTest.Queue;

import common.Student;
import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
*
1>PriorityQueue是一种无界的，线程不安全的队列
2>PriorityQueue是一种通过数组实现的，并拥有优先级的队列
3>PriorityQueue存储的元素要求必须是可比较的对象， 如果不是就必须明确指定比较器
* */
// 优先队列;
public class PriorityQueueTest {
    @Test
    public void test1(){
        PriorityQueue q = new PriorityQueue();
        q.add(1);
        q.offer(2); // 入列

        q.offer(4);
        q.add(3);
        System.out.println(q);
        for (Object o : q){
            System.out.println(o);
        }
        // 出列(有循序);
        System.out.println(q.poll());
        System.out.println(q.poll());
        System.out.println(q.poll());
        System.out.println(q.poll());
    }
    @Test
    public void test2(){
        //通过改造器指定排序规则
        PriorityQueue<Student> q = new PriorityQueue<Student>(new Comparator<Student>() {
            public int compare(Student o1, Student o2) {
                if(o1.getAge() == o2.getAge()){
                    return o1.getName().compareTo(o2.getName());
                }
                return o1.getAge() - o2.getAge();
            }
        });
        //入列
        q.offer(new Student("dafei", 20));
        q.offer(new Student("will", 17));
        q.offer(new Student("setf", 30));
        q.offer(new Student("bunny", 20));

        //出列
        System.out.println(q.poll());
        System.out.println(q.poll());
        System.out.println(q.poll());
        System.out.println(q.poll());
    }
}
