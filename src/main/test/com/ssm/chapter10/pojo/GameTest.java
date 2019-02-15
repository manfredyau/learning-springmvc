package com.ssm.chapter10.pojo;

import com.ssm.chapter10.game.Interceptor;
import com.ssm.chapter10.game.ProxyBeanFactory;
import com.ssm.chapter10.game.interceptor.RoleInterceptor;
import com.ssm.chapter10.game.service.RoleService;
import com.ssm.chapter10.game.service.impl.RoleServiceImpl;
import org.junit.Test;

public class GameTest {
    @Test
    public void gameTest() {
        RoleService roleService = new RoleServiceImpl();
        Interceptor interceptor = new RoleInterceptor();
        RoleService proxy = ProxyBeanFactory.getBean(roleService, interceptor);
        Role role = new Role(1L, "Manfred", "note for Manfred");
        proxy.printRole(role);

        System.out.println("############################# Test for afterThrowing method ####################################");
        role = null;
        proxy.printRole(role);
    }
}
