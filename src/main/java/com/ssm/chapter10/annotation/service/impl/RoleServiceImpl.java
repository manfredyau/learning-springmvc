package com.ssm.chapter10.annotation.service.impl;

import com.ssm.chapter10.annotation.pojo.Role;
import com.ssm.chapter10.annotation.service.RoleService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component("roleServiceImpl_anno")
public class RoleServiceImpl implements RoleService {
    public void printRoleInfo(Role role) {
        System.out.println("id = " + role.getId());
        System.out.println("roleName = " + role.getRoleName());
        System.out.println("note = " + role.getNote());
    }
}
