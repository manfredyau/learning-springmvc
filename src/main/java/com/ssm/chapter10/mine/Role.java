package com.ssm.chapter10.mine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("myrole")
public class Role {
    @Autowired
    Money money;

    public void printMoney() {
        money.print();
    }
}
