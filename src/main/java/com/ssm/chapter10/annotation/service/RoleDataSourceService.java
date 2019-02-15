package com.ssm.chapter10.annotation.service;

import com.ssm.chapter10.annotation.pojo.Role;

public interface RoleDataSourceService {
    Role getRole(Long id);
}
