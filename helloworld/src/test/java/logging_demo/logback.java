package logging_demo;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.core.ConsoleAppender;

import java.nio.charset.Charset;

public class logback {
    public static void main(String[] args) {
       //new BasicConfigurator();
        Logger log = new LoggerContext().getLogger(logback.class);
        ConsoleAppender ap =new ConsoleAppender<>();
        ap.setContext(new LoggerContext());
        // 输出格式;
        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setContext(new LoggerContext());
        encoder.setPattern("%date [%thread] %-5level [%logger{50}] %file:%line - %msg%n");
        encoder.setCharset(Charset.forName("UTF-8"));
        encoder.start();
        ap.setEncoder(encoder);
        // 启动;
        ap.start();
        log.addAppender(ap);
        log.error("xxxxxxx");
    }
}
