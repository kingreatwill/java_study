package com.demo.springdemo.services;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
//@RequestScope
@Scope("prototype")
public class TestService implements DisposableBean, InitializingBean {
    public String say(String msg){
        return msg;
    }



    @PostConstruct
    public void Inn(){
        System.out.println("111111111111111111111");
    }

    @PreDestroy
    public void Inn2(){
        System.out.println("22222222222222");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("2222222     2222222");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("111111111    1111111");
    }
}
