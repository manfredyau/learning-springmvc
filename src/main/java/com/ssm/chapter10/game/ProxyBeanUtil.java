package com.ssm.chapter10.game;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyBeanUtil implements InvocationHandler {
    // Proxied object
    private Object obj;

    private Interceptor interceptor;


    // Not allow to use constructor to create this object
    private ProxyBeanUtil() {
    }

    public static Object getBean(Object obj, Interceptor interceptor) {
        ProxyBeanUtil _this = new ProxyBeanUtil();
        _this.obj = obj;
        _this.interceptor = interceptor;
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), _this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object retObj = null;

        boolean exceptionFlag = false;

        interceptor.before(obj);

        try {
            // Reflect primitive method
            retObj = method.invoke(obj, args);
        } catch (Exception e) {
            exceptionFlag = true;
        } finally {
            interceptor.after(obj);
        }
        if (!exceptionFlag) {
            interceptor.afterReturning(obj);
        } else {
            interceptor.afterThrowing(obj);
        }
        return retObj;
    }
}
