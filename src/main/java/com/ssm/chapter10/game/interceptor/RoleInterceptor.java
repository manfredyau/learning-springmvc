package com.ssm.chapter10.game.interceptor;

import com.ssm.chapter10.game.Interceptor;

public class RoleInterceptor implements Interceptor {
    @Override
    public void before(Object obj) {
        System.out.println("Ready for printing role info...");
    }

    @Override
    public void after(Object obj) {
        System.out.println("Finish for printing role info...");
    }

    @Override
    public void afterReturning(Object obj) {
        System.out.println("Finish work, normally");
    }

    @Override
    public void afterThrowing(Object obj) {
        System.out.println("Something wrong...");
    }
}
