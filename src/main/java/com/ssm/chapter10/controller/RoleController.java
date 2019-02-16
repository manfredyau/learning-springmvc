package com.ssm.chapter10.controller;

import com.ssm.chapter10.pojo.Role;
import com.ssm.chapter10.service.RoleListService;
import com.ssm.chapter10.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class RoleController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleListService roleListService;

    public void errorUseServices() {
        Role role1 = new Role();
        role1.setRoleName("role1");
        role1.setNote("note1");
        roleService.insertRole(role1);

        Role role2 = new Role();
        role2.setRoleName("role2");
        role2.setNote("note2");
        roleService.insertRole(role2);
    }
}
