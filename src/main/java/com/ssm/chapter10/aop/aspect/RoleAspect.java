package com.ssm.chapter10.aop.aspect;

import com.ssm.chapter10.aop.verifier.RoleVerifier;
import com.ssm.chapter10.aop.verifier.impl.RoleVerifierImpl;
import com.ssm.chapter10.pojo.Role;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
//@Component
public class RoleAspect {
    @DeclareParents(value = "com.ssm.chapter10.aop.service.impl.RoleServiceImpl+", defaultImpl = RoleVerifierImpl.class)
    public RoleVerifier roleVerifier;

    @Pointcut("execution(* com.ssm.chapter10.aop.service.impl.RoleServiceImpl.printRole(..))")
    public void print() {
    }

    @Before("print() && args(role, sort)")
    public void before(Role role, int sort) {
        System.out.println("before ....");
    }

    @After("print()")
    public void after() {
        System.out.println("after ....");
    }

    @AfterReturning("print()")
    public void afterReturning() {
        System.out.println("afterReturning ....");
    }

    @AfterThrowing("print()")
    public void afterThrowing() {
        System.out.println("afterThrowing ....");
    }

    @Around("print()")
    public void around(ProceedingJoinPoint jp) {
        System.out.println("around before ....");
        try {
            jp.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("around after ....");
    }
}
