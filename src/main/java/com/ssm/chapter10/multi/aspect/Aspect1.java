package com.ssm.chapter10.multi.aspect;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Aspect1 {
    @Pointcut("execution(* com.ssm.chapter10.multi.bean.MultiBeanImpl.testMulti(..))")
    public void print() {
    }

    @Before("print()")
    public void before() {
        System.err.println("before1 ...");
    }

    @After("print()")
    public void after() {
        System.err.println("after1 ...");
    }

    @AfterReturning("print()")
    public void afterReturning() {
        System.err.println("afterReturning1 ...");
    }

    @AfterThrowing("print()")
    public void afterThrowing() {
        System.err.println("afterThrowing1 ...");
    }
}
