package com.cf.sessiontest.log4j;

import org.apache.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Demo {
    private static Logger logger=Logger.getLogger(Demo.class); // 获取logger实例
    private static Logger consoleLogger=Logger.getLogger("stdout"); // 获取logger实例
    private static Logger fileLogger=Logger.getLogger("all"); // 获取logger实例

    public static void main(String[] args) {
//        logger.info("普通Info信息");
//        logger.debug("调试debug信息");
//        logger.error("错误error信息");
//        logger.warn("警告warn信息");
//        logger.fatal("严重错误fatal信息");
//
//        //开发中有可能会遇到一下经典异常
//        logger.error("错误了",new IllegalArgumentException("非法参数异常"));

//        consoleLogger.info("普通Info信息");
//        consoleLogger.debug("调试debug信息");
//        consoleLogger.error("错误error信息");
//        consoleLogger.warn("警告warn信息");
//        consoleLogger.fatal("严重错误fatal信息");
//
//        //开发中有可能会遇到一下经典异常
//        consoleLogger.error("错误了",new IllegalArgumentException("非法参数异常"));

        fileLogger.info("普通Info信息");
        fileLogger.debug("调试debug信息");
        fileLogger.error("错误error信息");
        fileLogger.warn("警告warn信息");
        fileLogger.fatal("严重错误fatal信息");

        //开发中有可能会遇到一下经典异常
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            throw new IllegalArgumentException("非法参数异常");
        } catch (IllegalArgumentException e) {
//            e.printStackTrace(new PrintStream(baos));
            fileLogger.error("出错啦",e);
//            baos.reset();
//            fileLogger.error("错误error信息");
        }
//        fileLogger.error("错误了",new IllegalArgumentException("非法参数异常"));


    }
}
