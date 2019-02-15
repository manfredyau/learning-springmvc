package com.ssm.chapter10.xml.service.impl;

import com.ssm.chapter10.aop.verifier.RoleVerifier;
import com.ssm.chapter10.pojo.Role;
import com.ssm.chapter10.xml.service.RoleService;

public class RoleServiceImpl implements RoleService {
    public RoleVerifier roleVerifier;

    @Override
    public void printRole(Role role) {
        System.out.println("id = " + role.getId());
        System.out.println("roleName = " + role.getRoleName());
        System.out.println("note = " + role.getNote());
    }
}
