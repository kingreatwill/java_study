package com.demo.springdemo.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;


public class ExampleBean implements DisposableBean, InitializingBean {

    //@PostConstruct
    @Override
    public void destroy() {
        // do some destruction work (like releasing pooled connections)
        System.out.println("do some destruction work (like releasing pooled connections)");
    }

    //@PreDestroy
    @Override
    public void afterPropertiesSet() throws Exception {
        // do some initialization work
        System.out.println("do some initialization work");
    }
}
