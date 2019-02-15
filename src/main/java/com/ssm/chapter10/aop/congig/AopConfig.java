package com.ssm.chapter10.aop.congig;

import com.ssm.chapter10.aop.aspect.RoleAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.ssm.chapter10.aop"})
public class AopConfig {
    @Bean
    public RoleAspect getRoleAspect() {
        return new RoleAspect();
    }
}
