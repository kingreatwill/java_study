
public class Student {
    private String name;

    public Student() {
        System.out.println("无参构造器");
    }

    public Student(String name) {
        this.name = name;
    }
    {
        System.out.println("普通代码块！");
    }
    static  {
        System.out.println("这是静态代码块！");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
