
/**
 * 打印出测试类所有加载器
 */
public class ClassLoaderTest {
    public static void main(String[] args) {
        // 步骤一：获取当前的类加载器
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        // 步骤二：循环打印出每一层的类加载器
        while(classLoader!=null){
            String name = classLoader.getClass().getName();
            System.out.print("=====>" + name);
            classLoader= classLoader.getParent();
        }
        // 步骤三：遇到最上层类加载器退出然后打印出最上层的类加载器
        System.out.print("=====> " + classLoader);

    }
}
