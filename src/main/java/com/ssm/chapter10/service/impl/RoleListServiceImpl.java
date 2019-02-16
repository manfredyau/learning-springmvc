package com.ssm.chapter10.service.impl;

import com.ssm.chapter10.pojo.Role;
import com.ssm.chapter10.service.RoleListService;
import com.ssm.chapter10.service.RoleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleListServiceImpl implements RoleListService {
    @Autowired
    private RoleService roleService;

    private Logger logger = Logger.getLogger(RoleListServiceImpl.class);

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    public int insertRoleList(List<Role> roleList) {
        int count = 0;
        for (Role role : roleList) {
            try {
                System.err.println("###############################invoke insert######################################");
                count += roleService.insertRole(role);
            } catch (Throwable e) {
                logger.info(e);
            }
        }
        return count;
    }
}
