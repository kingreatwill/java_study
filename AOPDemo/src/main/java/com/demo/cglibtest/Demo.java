package com.demo.cglibtest;

import net.sf.cglib.proxy.*;

import java.lang.reflect.Method;

//CGLIB和Java动态代理的区别
//Java动态代理只能够对接口进行代理，不能对普通的类进行代理（因为所有生成的代理类的父类为Proxy，Java类继承机制不允许多重继承）；CGLIB能够代理普通类；
//Java动态代理使用Java原生的反射API进行操作，在生成类上比较高效；CGLIB使用ASM框架直接对字节码进行操作，在类的执行过程中比较高效
public class Demo {
    public void test(){
        System.out.println("hello world fro Demo");
    }

    // https://blog.csdn.net/gyshun/article/details/81000997
    public static void main(String[] args) {
        //test1();
        //test2();
        test3();
    }

    // InvocationHandler invoke方法来替换直接访问类的方法，但是你必须注意死循环。因为invoke中调用的任何原代理类方法，均会重新代理到invoke方法中。
    // https://blog.csdn.net/danchu/article/details/70146985
    public static void test3(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallback(new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if(method.getDeclaringClass() != Object.class && method.getReturnType() == String.class){
                    return "hello cglib";
                }else{
                    throw new RuntimeException("Do not know what to do");
                }
            }
        });
        SampleClass proxy = (SampleClass) enhancer.create();
        proxy.test(null);
        proxy.toString();
    }

    // FixedValue loadObject拦截返回值
    public static void test2(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallback(new FixedValue() {
            @Override
            public Object loadObject() throws Exception {
                return "Hello cglib";
            }
        });
        SampleClass proxy = (SampleClass) enhancer.create();
        System.out.println(proxy.test(null)); //拦截test，输出Hello cglib
        System.out.println(proxy.toString());
        System.out.println(proxy.getClass()); // 没有对getClass进行拦截
        System.out.println(proxy.hashCode()); // 返回的是"Hello cglib"所以异常。 Exception in thread "main" java.lang.ClassCastException: java.lang.String cannot be cast to java.lang.Number
    }

    // MethodInterceptor intercept 拦截方法
    public static void test1(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Demo.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println("before method run...");
                Object result = proxy.invokeSuper(obj, args);
                System.out.println("after method run...");
                return result;
            }
        });
        Demo sample = (Demo) enhancer.create();
        sample.test();
    }
}
