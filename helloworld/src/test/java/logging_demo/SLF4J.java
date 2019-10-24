package logging_demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SLF4J {


    public static void main(String[] args) {

        // jdk自带的logging->简称 jul (java-util-logging)
        //apache commons-logging->简称 jcl

        // 按照一套统一的API来进行日志编程，实际的日志框架来实现这套API，这样的话，即使更换日志框架，也可以做到无缝切换。这就是commons-logging与slf4j的初衷。
        // ServiceLoader 机制无缝切换日志框架

        //logback-classic 自带
        //log4j-slf4j-impl
        //slf4j-jdk14
        final Logger log = LoggerFactory.getLogger(SLF4J.class);// 默认使用logback组件;


       // final Logger log = new Log4jLoggerFactory().getLogger("SLF4J.class");
        log.trace("Hello World!");
        log.debug("How are you today?");
        log.info("I am fine.");
        log.warn("I love programming.");
        log.error("I am programming.");
    }
}
