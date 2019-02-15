package com.ssm.chapter10.multi.bean;

import org.springframework.stereotype.Component;

@Component
public class MultiBeanImpl implements MultiBean {
    @Override
    public void testMulti() {
        System.out.println("test multi aspect!");
    }
}
