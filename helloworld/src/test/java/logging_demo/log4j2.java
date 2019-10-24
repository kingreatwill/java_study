package logging_demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.DefaultConfiguration;

import java.io.IOException;

// https://www.cnblogs.com/jiyukai/p/9418463.html
public class log4j2 {

    public static void main(String[] args) throws IOException {
//        File file = new File("src/main/resources/log4j2.xml");
//        BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
//        final ConfigurationSource source = new ConfigurationSource(in);
//        Configurator.initialize(null, source);
        Configurator.initialize(new DefaultConfiguration());
        //LogManager.getContext()
         Logger log = LogManager.getLogger();
        String thing = args.length > 0 ? args[0] : "world";
        log.error("xxxx");
        log.info("Hello, {}!", thing);
        log.debug("Got calculated value only if debug enabled: {}", () -> doSomeCalculation());
    }
    private static Object doSomeCalculation() {
        // do some complicated calculation
        return "oooooo";
    }
}
