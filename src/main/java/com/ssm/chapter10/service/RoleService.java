package com.ssm.chapter10.service;

import com.ssm.chapter10.pojo.Role;

import java.util.List;

public interface RoleService {
    int insertRole(Role role);

    default int insertRoleList(List<Role> list) {
        return -1;
    }
}
