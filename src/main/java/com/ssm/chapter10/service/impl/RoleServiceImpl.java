package com.ssm.chapter10.service.impl;

import com.ssm.chapter10.pojo.Role;
import com.ssm.chapter10.service.RoleService;
import com.ssm.chapter10.sql.mapper.RoleMapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("roleServiceImpl13")
public class RoleServiceImpl implements RoleService, ApplicationContextAware {
    @Autowired
    private RoleMapper roleMapper;

    private ApplicationContext applicationContext;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public int insertRole(Role role) {
        return roleMapper.insertRole(role);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public int insertRoleList(List<Role> roleList) {
        // 此處對 Spring 產生了依賴
        RoleService roleService = applicationContext.getBean(RoleService.class);
        int count = 0;
        for (Role role : roleList) {
            count += roleService.insertRole(role);
        }
        return count;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
