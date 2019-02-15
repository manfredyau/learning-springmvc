package com.ssm.chapter10.annotation.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component(value = "role")
public class Role {
    @Value("1")
    private Long id;

    @Value("Manfred")
    private String roleName;

    @Value("Learning SpringMVC")
    private String note;

    public Role() {
    }

    public Role(String roleName, String note) {
        this.roleName = roleName;
        this.note = note;
    }

    public Role(Long id, String roleName, String note) {
        this.id = id;
        this.roleName = roleName;
        this.note = note;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
