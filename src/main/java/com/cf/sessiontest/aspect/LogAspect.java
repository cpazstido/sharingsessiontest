package com.cf.sessiontest.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * aop日志处理
 * @author Mr.zou
 *
 */
@Aspect
@Component
public class LogAspect {
//    @Resource
//    private LogService logService;  

    private  static  final Logger logger = LoggerFactory.getLogger(LogAspect. class);  
    private long start = 0;
    //Controller层切点，这里如果需要配置多个切入点用“||”
    //com.cf.sessiontest.service.impl
    //com.cf.sessiontest.service
    @Pointcut("execution(* com.cf.sessiontest.service.IUserService.testTransaction())")
    public void controllerAspect() {  
    }

    /**
     * 异常通知 用于拦截记录异常日志
     *
     * @param joinPoint
     * @param e
     */
//    @AfterThrowing(pointcut = "controllerAspect()", throwing="e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //请求的IP
        String ip = request.getRemoteAddr();
        //获取用户请求方法的参数并序列化为JSON格式字符串


    }

    /** 
     * 前置通知 用于拦截Controller层记录用户的操作 
     * 
     * @param joinPoint 切点 
     */ 
    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) {
    	start = System.currentTimeMillis();
        System.out.println(start);
    }
    /**
     * 
     * @param joinPoint
    */
    //配置controller环绕通知,使用在方法aspect()上注册的切入点
//     @Around("controllerAspect()")
//      public void around(JoinPoint joinPoint){
//          //System.out.println("==========开始执行controller环绕通知===============");
//          long start = System.currentTimeMillis();
//          try {
//              ((ProceedingJoinPoint) joinPoint).proceed();
//              long end = System.currentTimeMillis();
//              if(logger.isInfoEnabled()){
//                  logger.info("around " + joinPoint + "\tUse time : " + (end - start) + " ms!");
//              }
//              //System.out.println("==========结束执行controller环绕通知===============");
//          } catch (Throwable e) {
//        	  //System.out.println(e.getMessage());
//              long end = System.currentTimeMillis();
//              if(logger.isInfoEnabled()){
//                  logger.info("around " + joinPoint + "\tUse time : " + (end - start) + " ms with exception : " + e.getMessage());
//              }
//          }
//      }
 

    //配置后置返回通知,使用在方法aspect()上注册的切入点
//      @AfterReturning(pointcut = "controllerAspect()",returning = "retu")
//      public void afterReturn(JoinPoint joinPoint,Object retu){
//    	  HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//    	  //请求的IP
//
//      }


}