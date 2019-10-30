package com.demo.PrometheusDemo;

import com.sun.net.httpserver.HttpServer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class StartApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        ApplicationContext applicationContext = contextRefreshedEvent.getApplicationContext();
        ApplicationContext parent = applicationContext.getParent();
        HttpServer server = PrometheusDemoApplication.getServer();
        if (parent == null) {
//            CacheManager cacheManager = applicationContext.getBean(CacheManager.class);
//            Cache cache = cacheManager.getCache(MsConstants.NODE_CACHE_NAME);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //代码
                    server.start();
                }
            }).start();
        }
    }
}