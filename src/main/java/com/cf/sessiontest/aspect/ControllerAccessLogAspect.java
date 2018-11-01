package com.cf.sessiontest.aspect;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect
@Component
public class ControllerAccessLogAspect {
    private  static  final Logger logger = LoggerFactory.getLogger(ControllerAccessLogAspect. class);
    @Pointcut("execution(* com.cf..*.controller..*.*(..))")
    public void controllerAspect() {
    }

//    @Around("controllerAspect()")
//    public Object around(ProceedingJoinPoint pjp) throws  Throwable{
//        Object args[] = pjp.getArgs();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        logger.debug("=================="+sdf.format(new Date())+" "+pjp.getTarget().getClass().getSimpleName()+" "+pjp.getSignature().getName()+"==================");
//        for(Object arg:args){
//            if(arg.getClass().getName().contains("com.cf"))
//                logger.debug("参数："+ JSON.toJSONString(arg));
//        }
//        Object re = pjp.proceed();
//        return re;
//    }
}
