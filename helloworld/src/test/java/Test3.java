import org.junit.jupiter.api.Test;

public class Test3 {
    @Test
    public void test1(){
        System.getProperties().list(System.out);
        System.out.println(System.getProperty("user.name"));
        System.out.println(System.getProperty("java.library.path"));
    }

    @Test
    public void test2(){

        Integer v1 = Integer.valueOf(10000) ;
        Integer v2 = Integer.valueOf(10000);
        System.out.println(v1==v2);

        Integer v3 = Integer.valueOf(100);
        Integer v4 = Integer.valueOf(100);
        System.out.println(v3==v4);


        int v5 = 10000;
        int v6 = 10000;
        System.out.println(v5==v6);
    }
    /*
    *
    * 事实上，除 Float 和 Double 外，其他包装数据类型都会缓存，6 个包装类直接赋值时，就是调用对应包装类的静态工厂方法 valueOf()。

各个包装类的缓存区间如下：

Boolean：使用静态 final 变量定义，valueOf() 就是返回这两个静态值
Byte：表示范围是 -128 ~ 127，全部缓存
Short：表示范围是 - 32768 ~ 32767，缓存范围是 -128~127
Character：表示范围是 0 ~ 65535，缓存范围是 0~127
Long：表示范围是 [-2^63 ~ 2^63-1]，缓存范围是 -128~127
Integer：表示范围是 [-2^31 ~ 2^31-1]，缓存范围是 -128~127，但它是唯一可以修改缓存范围的包装类，在 VM options 加入参数 -XX:AutoBoxCacheMax=6666，即可设置最大缓存值为 6666
    * */
}
