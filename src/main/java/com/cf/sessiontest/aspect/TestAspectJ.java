package com.cf.sessiontest.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

//@Aspect
@Component
public class TestAspectJ {

    public void doBefore(JoinPoint joinPoint) {
        System.out.println("doBefore");
    }

    public void doAfter(JoinPoint joinPoint){
        System.out.println("doAfter");
    }

    public Object doAround(ProceedingJoinPoint jp)  throws Throwable{
        System.out.println("doAround begin");
        Object rvt=jp.proceed(jp.getArgs());
        System.out.println("doAround after");
        return "world";
    }

    public void doAfterReturnning(JoinPoint joinPoint,Object rtv){
        System.out.println("doAfterReturnning："+rtv);
    }

    public void doAfterThrowing(Throwable ex){
        System.out.println("doAfterThrowing");
    }
}
