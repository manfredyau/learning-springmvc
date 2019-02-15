package com.ssm.chapter10.game.service.impl;

import com.ssm.chapter10.game.service.RoleService;
import com.ssm.chapter10.pojo.Role;

public class RoleServiceImpl implements RoleService {
    @Override
    public void printRole(Role role) {
        System.out.println("{id = " + role.getId()
                + ", roleName = " + role.getRoleName()
                + ", note = " + role.getNote() + "}");
    }
}
