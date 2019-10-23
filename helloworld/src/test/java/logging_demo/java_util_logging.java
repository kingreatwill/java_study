package logging_demo;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

//Logger 对外发布的日志记录器，应用系统可以通过该对象完成日志记录的功能
//
//Level 日志的记录级别
//
//LoggingMXBean 接口对象，对外发布的日志管理器
//
//LogRecord 日志信息描述对象
//
//LoggerManager 日志管理器
//
//Filter 日志过滤器，接口对象，在日志被 Handler 处理之前，起过滤作用
//
//Handler 日志处理器，接口对象，决定日志的输出方式
//
//Formatter 日志格式化转换器，接口对象，决定日志的输出格式 (Handler)
public class java_util_logging {
    // https://segmentfault.com/a/1190000004227150
    private static Logger log = Logger.getLogger(java_util_logging.class.toString());
    static {
        Handler console = new ConsoleHandler();//FileHandler  SocketHandler
       // console.setFormatter();
        console.setLevel(Level.SEVERE);
        log.addHandler(console);
    }
    public static void main(String[] args) {
        // all→finest→finer→fine→config→info→warning→server→off
        // 级别依次升高，后面的日志级别会屏蔽之前的级别
        //log.setLevel(Level.INFO);
        log.finest("finest");
        log.finer("finer");
        log.fine("fine");
        log.config("config");
        log.info("info");
        log.warning("warning");
        log.severe("server");
    }
}
