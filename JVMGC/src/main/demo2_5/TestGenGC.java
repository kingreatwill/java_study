
public class TestGenGC {
    public static void main(String[] args) {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            String s = String.valueOf(i).intern();// 加入到常量池中
        }
    }
}
