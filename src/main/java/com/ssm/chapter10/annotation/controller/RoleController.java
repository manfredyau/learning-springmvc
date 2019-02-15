package com.ssm.chapter10.annotation.controller;

import com.ssm.chapter10.annotation.pojo.Role;
import com.ssm.chapter10.annotation.service.RoleService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class RoleController {
//    @Autowired
//    @Qualifier("roleService3")
    private RoleService roleService;

    public RoleController(@Qualifier("roleServiceImpl_anno") RoleService roleService) {
        this.roleService = roleService;
    }
    public void printRole(Role role) {
        roleService.printRoleInfo(role);
    }
}
