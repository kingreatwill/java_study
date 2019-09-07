package se;

import io.helidon.health.HealthSupport;
import io.helidon.health.checks.HealthChecks;
import io.helidon.media.jsonp.server.JsonSupport;
import io.helidon.webserver.Routing;
import io.helidon.webserver.ServerConfiguration;
import io.helidon.webserver.SocketConfiguration;
import io.helidon.webserver.WebServer;
import org.eclipse.microprofile.health.HealthCheckResponse;

// https://helidon.io/docs/latest
public class MainSE {
    public  static  void main(String... args){


        Routing healthRouting = Routing.builder()
                .register(JsonSupport.create())
                .register(HealthSupport.builder()
                        .webContext("/live")
                        .add(HealthChecks.healthChecks())
                        .build())
                .register(HealthSupport.builder()
                        .webContext("/ready")
                        .add(() -> HealthCheckResponse.named("database").up().build())
                        .build())
                .build();

        Routing defaultRouting = Routing.builder()
                .get("/greet", (req, res)
                        -> res.send("Hello World!"))
                .any((req, res) -> res.send("It works!"))
                .build();

        WebServer server = WebServer.builder(defaultRouting)
                .config(ServerConfiguration.builder()
                        .port(8080)
                        .addSocket("health", SocketConfiguration.builder()
                                .port(8081)
                                .build())
                        .build())
                .addNamedRouting("health", healthRouting)
                .build();

        server.start();


//        WebServer.create(
//                Routing.builder()
//                        .get("/greet", (req, res)
//                                -> res.send("Hello World!"))
//                        .build())
//                .start();
    }
}
