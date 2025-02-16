package com.ssm.chapter10.controller;

import com.ssm.chapter10.pojo.Role;
import com.ssm.chapter10.service.RoleListService;
import com.ssm.chapter10.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("controller2")
public class RoleController {
    @Autowired
    @Qualifier("roleServiceImpl13")
    private RoleService roleService;

    @Autowired
    private RoleListService roleListService;

    @Transactional(propagation = Propagation.REQUIRED)
    //這個是示範不良的做法
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

    @RequestMapping("/addRole")
    @ResponseBody
    public Role addRole(Role role) {
        roleService.insertRole(role);
        return role;
    }
}
