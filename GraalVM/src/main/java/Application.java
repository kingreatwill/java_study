import org.graalvm.polyglot.Context;
// mvn clean package
// 注意需要安装graalvm 可以通过sdkman 安装
// 运行./target/GraalVM
public class Application {
    public static void main(String[] args) {
        System.out.println("Hello Java!");
        try (Context context = Context.create()) {
            context.eval("js", "print('Hello JavaScript!');");
        }
    }
}
