package com.cf.sessiontest.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory {
    private static ProxyFactory instance = new ProxyFactory();
    private ProxyFactory() {}

    public static ProxyFactory getInstance() {
        return instance;
    }

    @SuppressWarnings("unchecked")
    public <T> T create(final T t) {
        return (T) Proxy.newProxyInstance(t.getClass().getClassLoader(), t.getClass().getInterfaces(), new InvocationHandler() {

            /**
             * 当使用创建的代理对象执行其中的方法时，都会转换为调用与代理对象绑定的InvocationHandler对象的invoke方法，
             * 这样我们就可以在这个方法里面对调用情况进行一些特定的处理逻辑
             */
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("正在调用的方法是：" + method);
                //1、加入对调用方法前的处理逻辑
                //...
                Object result = null;
                try {
                    //2、正常的调用目标对象的目标方法
                    result = method.invoke(t, args);
                    //3、可加入正常调用后的处理逻辑
                    //...
                } catch (Exception e) {
                    //4、可加入目标对象的方法调用抛出异常后的处理逻辑
                    //..
                } finally {
                    //5、可加入目标对象的方法执行完成后的处理逻辑，此逻辑不论是否抛出异常都将执行
                }
                return "world";
            }

        });
    }
}
