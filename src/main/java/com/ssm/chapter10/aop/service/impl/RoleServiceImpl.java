package com.ssm.chapter10.aop.service.impl;

import com.ssm.chapter10.aop.service.RoleService;
import com.ssm.chapter10.pojo.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleServiceImpl implements RoleService {
    @Override
    public void printRole(Role role, int sort) {
        System.out.println("{id: " + role.getId() + ", "
                + "roleName: " + role.getRoleName() + ", "
                + "note: " + role.getNote()
                + "}");
        System.out.println(sort);
    }
}
