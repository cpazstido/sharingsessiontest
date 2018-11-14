package com.cf.sessiontest.oom;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * jdk7
 * -XX:PermSize=10M -XX:MaxPermSize=10M
 *
 * jdk8
 * -XX:MetaspaceSize=10M -XX:MaxMetaspaceSize=10M
 */
public class JavaMethodAreaOOM {
    public static void main(String[] args) {
        int i=0;
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOM.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {

                @Override
                public Object intercept(Object obj, Method arg1, Object[] args, MethodProxy proxy) throws Throwable {
                    return proxy.invokeSuper(obj, args);
                }
            });
            OOM oom = (OOM) enhancer.create();
            oom.sayHello("Kevin LUAN");
            System.out.println(i++);
        }
    }

    static class OOM {
        public String sayHello(String str) {
            return "HI " + str;
        }
    }
}
