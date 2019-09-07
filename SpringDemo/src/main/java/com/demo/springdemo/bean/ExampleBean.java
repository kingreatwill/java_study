package com.demo.springdemo.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Size;

@Service
@Validated
public class ExampleBean implements DisposableBean, InitializingBean {

    @Cacheable(value="findByCode", key="#code") //@EnableCaching  //开启缓存
    public String findByCode(@Size(min = 8, max = 10) String code) {
        return code;
    }


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
