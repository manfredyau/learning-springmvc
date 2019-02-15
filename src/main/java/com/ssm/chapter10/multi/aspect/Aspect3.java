package com.ssm.chapter10.multi.aspect;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Aspect3 {
    @Pointcut("execution(* com.ssm.chapter10.multi.bean.MultiBeanImpl.testMulti(..))")
    public void print() {
    }

    @Before("print()")
    public void before() {
        System.out.println("before3 ...");
    }

    @After("print()")
    public void after() {
        System.out.println("after3 ...");
    }

    @AfterReturning("print()")
    public void afterReturning() {
        System.out.println("afterReturning3 ...");
    }

    @AfterThrowing("print()")
    public void afterThrowing() {
        System.out.println("afterThrowing3 ...");
    }
}
