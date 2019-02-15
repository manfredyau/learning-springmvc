package com.ssm.chapter10.annotation.service.impl;

import com.ssm.chapter10.annotation.pojo.Role;
import com.ssm.chapter10.annotation.service.RoleService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("RoleService2")
public class RoleServiceImpl2 implements RoleService2 {
    @Autowired
    private Role role;


    public void printRoleInfo() {
        System.err.println("id = " + role.getId());
        System.err.println("roleName = " + role.getRoleName());
        System.err.println("note = " + role.getNote());
    }
}
