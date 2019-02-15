package com.ssm.chapter10.aspect;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class ProxyFactoryBeanFactory implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("前置通知!");
    }
}
