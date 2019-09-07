package se;

import io.helidon.webserver.Routing;
import io.helidon.webserver.WebServer;
// https://helidon.io/docs/latest
public class MainSE {
    public  static  void main(String... args){
        WebServer.create(
                Routing.builder()
                        .get("/greet", (req, res)
                                -> res.send("Hello World!"))
                        .build())
                .start();
    }
}
