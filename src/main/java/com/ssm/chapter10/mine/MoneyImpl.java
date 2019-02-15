package com.ssm.chapter10.mine;

import org.springframework.stereotype.Component;

@Component
public class MoneyImpl implements Money {
    @Override
    public void print() {
        System.out.println("I am money");
    }
}
