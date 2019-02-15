package com.ssm.chapter10.xml.aspect;

import com.ssm.chapter10.pojo.Role;
import org.aspectj.lang.ProceedingJoinPoint;

public class XmlAspect {
    public void before(Role role) {
        System.out.println("role_id = " + role.getId() + "before ...");
//        System.out.println("before ...");
    }

    public void after() {
        System.out.println("after ...");
    }

    public void afterReturning() {
        System.out.println("after-returning ...");
    }

    public void afterThrowing() {
        System.out.println("after-throwing ...");
    }

    public void around(ProceedingJoinPoint jp) {
        System.out.println("around before ...");
        try {
            jp.proceed();
        } catch (Throwable e) {
            new RuntimeException("回調原有流程，產生異常");
        }
        System.out.println("around after ...");
    }
}
