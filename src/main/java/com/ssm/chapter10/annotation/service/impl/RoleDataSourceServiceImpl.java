package com.ssm.chapter10.annotation.service.impl;

import com.ssm.chapter10.annotation.pojo.Role;
import com.ssm.chapter10.annotation.service.RoleDataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class RoleDataSourceServiceImpl implements RoleDataSourceService {
    @Autowired
    DataSource dataSource;

    public Role getRole(Long id) {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        Role role = null;
        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement("select id, role_name, note from t_role where id = ?");
            ps.setLong(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                role = new Role();
                role.setId(rs.getLong("id"));
                role.setRoleName(rs.getString("role_name"));
                role.setNote(rs.getString("note"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return role;
    }
}
