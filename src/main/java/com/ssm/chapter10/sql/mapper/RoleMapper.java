package com.ssm.chapter10.sql.mapper;

import com.ssm.chapter10.pojo.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMapper {
    int insertRole(Role role);

    int deleteRole(Long id);

    Role getRole(@Param("id") Long id);

    int updateRole(Role role);
}
