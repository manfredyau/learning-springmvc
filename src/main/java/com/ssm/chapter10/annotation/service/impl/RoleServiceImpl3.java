package com.ssm.chapter10.annotation.service.impl;

import com.ssm.chapter10.annotation.pojo.Role;
import com.ssm.chapter10.annotation.service.RoleService;
import org.springframework.stereotype.Component;

@Component("roleService3")
public class RoleServiceImpl3 implements RoleService {
    public void printRoleInfo(Role role) {
        System.err.print("{id = " + role.getId());
        System.err.print(", roleName = " + role.getRoleName());
        System.err.println(", note = " + role.getNote() + "}");

    }
}
