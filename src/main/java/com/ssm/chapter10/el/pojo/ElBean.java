package com.ssm.chapter10.el.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("elBean")
public class ElBean {
    @Value("#{role_EL}")
    private Role role;

    @Value("#{role_EL.id}")
    private Long id;

    @Value("#{role_EL.getNote()?.toString()}")
    private String note;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
