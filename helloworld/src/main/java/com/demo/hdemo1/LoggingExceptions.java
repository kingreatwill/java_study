package com.demo.hdemo1;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.*;
import java.io.*;

class LoggingException extends Exception {
    private static Logger logger =  Logger.getLogger("LoggingException");
    LoggingException() {
        StringWriter trace = new StringWriter();
        printStackTrace(new PrintWriter(trace));
        // 严重级别;
        logger.severe(trace.toString());
    }
}
public class LoggingExceptions {
    private static Logger logger =  Logger.getLogger("LoggingExceptions2");
    static void logException(Exception e) {
        StringWriter trace = new StringWriter();
        e.printStackTrace(new PrintWriter(trace));
        logger.severe(trace.toString());
    }

    public static void main(String[] args) {
        try {
            throw new LoggingException();
        } catch(LoggingException e) {
            System.err.println("Caught " + e);
        }
        try {
            throw new LoggingException();
        } catch(LoggingException e) {
            System.err.println("Caught " + e);
        }

        try {
            throw new NullPointerException();
        } catch(NullPointerException e) {
            logException(e);
        }

        try {
            throw new Exception("My Exception");
        }catch(LoggingException | NullPointerException e){

        } catch(Exception e) {
            System.out.println("Caught ExceptionXXXXXXXXXX");
            System.out.println(
                    "getMessage():" + e.getMessage());
            System.out.println("getLocalizedMessage():" +
                    e.getLocalizedMessage());
            System.out.println("toString():" + e);
            System.out.println("printStackTrace():");
            e.printStackTrace(System.out);
            System.out.println("xxxxxxxxxxxxxxxxxxxxx:");
            for(StackTraceElement ste : e.getStackTrace())
                System.out.println(ste.getMethodName());
        }
    }
}
