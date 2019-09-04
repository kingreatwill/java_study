import java.util.Properties;

public class JVMOptionsTest {
    public static void main(String[] args) {
        //获取所有的属性
        Properties properties = System.getProperties();
        //遍历所有的属性
        for (String key : properties.stringPropertyNames()) {
            //输出对应的键和值
            System.out.println(key + "=" + properties.getProperty(key));
        }
        String myname2 = System.getProperty("os.name");
        System.out.println("从jvm系统中获取的myname变量值为 = " + myname2);
    }
}
