package com.example.podinfo;

import com.sun.net.httpserver.HttpServer;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.common.TextFormat;
import io.prometheus.client.hotspot.DefaultExports;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.InetSocketAddress;

@SpringBootApplication
public class PodinfoApplication {

    public static void main(String[] args) {
        getServer();
        SpringApplication.run(PodinfoApplication.class, args);
    }


    public static void getServer() {
        CollectorRegistry.defaultRegistry.clear();
        DefaultExports.register(CollectorRegistry.defaultRegistry);
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(9999), 0);
            server.createContext("/metrics", httpExchange -> {
                StringWriter writer = new StringWriter();
                TextFormat.write004(writer, CollectorRegistry.defaultRegistry.metricFamilySamples());
                String response = writer.toString();
                httpExchange.sendResponseHeaders(200, response.getBytes().length);
                try (OutputStream os = httpExchange.getResponseBody()) {
                    os.write(response.getBytes());
                }
            });
            new Thread(server::start).start();
            //return server;
        } catch (IOException e) {
            //throw new RuntimeException(e);
        }
    }

}
