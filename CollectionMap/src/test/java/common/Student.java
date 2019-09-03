package common;

public class Student implements Comparable<Student>{
    private String name;
    private int age;
    public Student() {
    }
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public String getName() { return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) { this.age = age;
    }
    @Override
    public String toString() { return "common.Student{" +
            "name='" + name + '\'' + ", age=" + age +
            '}';
    }

    @Override
    public int compareTo(Student o) {
        return this.age-o.age;//升序
    }
}
