package com.test.aop.cglib.annotation;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: zhouguanya
 * @Date: 2018/8/250 19:100
 * @Description: 横切关注点,打印开始和结束的时间
 */
@Component
@Aspect
public class CglibAnnotationHandler {
    /**
     * 定义切点
     */
    @Pointcut("execution(* com.test.aop.cglib.annotation.*.*(..))")
    public void location() {

    }

    /**
     * 前置通知
     * 打印开始时间
     */
    @Before("location()")
    public void startEatFruitDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String startEatDate = simpleDateFormat.format(new Date());
        System.out.println("开始的时间是：" + startEatDate);
    }

    /**
     * 后置通知
     * 打印结束的时间
     */
    @After("location()")
    public void endEatFruitDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String endEatDate = simpleDateFormat.format(new Date());
        System.out.println("结束的时间是：" + endEatDate);
    }
}
