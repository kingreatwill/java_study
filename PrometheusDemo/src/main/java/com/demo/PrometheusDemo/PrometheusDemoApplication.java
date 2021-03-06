package com.demo.PrometheusDemo;

import com.sun.net.httpserver.HttpServer;
import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import io.prometheus.client.hotspot.DefaultExports;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

@SpringBootApplication
public class PrometheusDemoApplication  {

	public static void main(String[] args) {
	    // 1
//        run();
		//SpringApplication.run(PrometheusDemoApplication.class, args);//spring.profiles.active=dev 配置文件指定

        // 2
//        SpringApplication app = new SpringApplication(PrometheusDemoApplication.class);
//        app.addListeners(new StartApplicationListener());
//        app.setAdditionalProfiles("dev"); // 指定application-dev.properties
//        app.run(args);

        //变更配置文件读取位置启动
        new SpringApplicationBuilder(PrometheusDemoApplication.class)
                .properties("spring.config.location=classpath:/springbootconfig.properties")
                .run(args);


//        new SpringApplicationBuilder(PrometheusDemoApplication.class)
//                .web(WebApplicationType.SERVLET) //.NONE .REACTIVE, .SERVLET
//                .profiles("dev") // 指定application-dev.properties
//                .run(args);

       //  @PropertySource("classpath:define.properties") 指定自定义文件
	}

	// 1. 必须放到启动类中;
    //@Bean
    public ConfigurableServletWebServerFactory servletWebServerFactory(){
        //JettyServletWebServerFactory
                // TomcatServletWebServerFactory
        //UndertowServletWebServerFactory
        ConfigurableServletWebServerFactory f = new JettyServletWebServerFactory(8081) ;
        //f.setDisplayName("");
        return f;

        /*
        * 如果使用Jetty容器，那么添加

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jetty</artifactId>
</dependency>
如果使用Undertow容器，那么添加

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-undertow</artifactId>
</dependency
        * */
    }

    // 2. implements WebServerFactoryCustomizer<JettyServletWebServerFactory>;
    // 2. implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>;
    //@Override
    public void customize(ConfigurableServletWebServerFactory factory) {
        //factory.setPort(8082);
    }

    public static void run() {
        HttpServer server = getServer();
        new Thread(server::start).start();

//        PrometheusMeterRegistry prometheusRegistry = new PrometheusMeterRegistry(PrometheusConfig.DEFAULT);
//        prometheusRegistry.config().commonTags("application", "MYAPPNAME");
//        DefaultExports.register(prometheusRegistry.getPrometheusRegistry());
//        try {
//            HttpServer server = HttpServer.create(new InetSocketAddress(9999), 0);
//            server.createContext("/metrics", httpExchange -> {
//                String response = prometheusRegistry.scrape();
//                httpExchange.sendResponseHeaders(200, response.getBytes().length);
//                try (OutputStream os = httpExchange.getResponseBody()) {
//                    os.write(response.getBytes());
//                }
//            });
//            new Thread(server::start).start();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    public static HttpServer getServer(){
        PrometheusMeterRegistry prometheusRegistry = new PrometheusMeterRegistry(PrometheusConfig.DEFAULT);
        prometheusRegistry.config().commonTags("application", "MYAPPNAME");
        DefaultExports.register(prometheusRegistry.getPrometheusRegistry());
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(9999), 0);
            server.createContext("/metrics", httpExchange -> {
                String response = prometheusRegistry.scrape();
                httpExchange.sendResponseHeaders(200, response.getBytes().length);
                try (OutputStream os = httpExchange.getResponseBody()) {
                    os.write(response.getBytes());
                }
            });
           return server;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
