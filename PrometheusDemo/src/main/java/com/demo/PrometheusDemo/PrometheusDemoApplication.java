package com.demo.PrometheusDemo;

import com.sun.net.httpserver.HttpServer;
import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import io.prometheus.client.hotspot.DefaultExports;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

@SpringBootApplication
public class PrometheusDemoApplication {

	public static void main(String[] args) {
        run();
		SpringApplication.run(PrometheusDemoApplication.class, args);
	}

    public static void run() {
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
            new Thread(server::start).start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
