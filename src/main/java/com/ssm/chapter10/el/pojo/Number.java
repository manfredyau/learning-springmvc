package com.ssm.chapter10.el.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Number {
    @Value("#{T(Math).random() * 100}")
    private double PI;

    public double getPI() {
        return PI;
    }

    public void setPI(double PI) {
        this.PI = PI;
    }
}
