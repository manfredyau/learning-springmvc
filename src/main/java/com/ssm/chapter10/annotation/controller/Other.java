package com.ssm.chapter10.annotation.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Other {
    @Bean(name = "util", initMethod = "init", destroyMethod = "myDestroy")
    public Util initUtil() {
        Util util = new Util();
        util.setProp1("屬性1");
        util.setProp2("屬性2");
        return util;
    }
}
