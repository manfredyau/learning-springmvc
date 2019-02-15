import com.ssm.chapter10.pojo.Role;
import com.ssm.chapter10.sql.mapper.RoleMapper;
import org.junit.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.StatementCallback;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

import java.sql.*;
import java.util.List;

public class JDBCTest {
    @Test
    public void test1() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config-12.xml");
        JdbcTemplate jdbcTemplate = applicationContext.getBean(JdbcTemplate.class);
        Long id = 1L;
        //language=MySQL
        String sql = "select id, role_name, note from t_role where id = " + id;
        Role role = jdbcTemplate.queryForObject(sql, (ResultSet resultSet, int rowNum) -> {
            Role result = new Role();
            result.setId(resultSet.getLong("id"));
            result.setRoleName(resultSet.getString("role_name"));
            result.setNote(resultSet.getString("note"));
            return result;
        });
        System.err.println(role.getRoleName());
    }

    @Test
    public void test2() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config-12.xml");
        JdbcTemplate jdbcTemplate = applicationContext.getBean(JdbcTemplate.class);
//        Role role = new Role("Manfred", "Jython");
//        int affected = insertRole(jdbcTemplate, role);
//        System.out.println("影響了 " + affected + " 行");
//
//        affected = deleteRole(jdbcTemplate, 4L);
//        System.out.println("影響了 " + affected + " 行");

        List<Role> result = findRole(jdbcTemplate, "Manfred");
        for (Role role1 : result) {
            System.out.println(role1.getId());
        }
    }

    @Test
    public void test3() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config-12.xml");
        JdbcTemplate jdbcTemplate = applicationContext.getBean(JdbcTemplate.class);
        Role role = getRoleByStatementCallback(jdbcTemplate, 5L);
        System.out.println(role.getId());
    }

    @Test
    public void test4() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config-mybatis.xml");
        SqlSessionTemplate sqlSessionTemplate = applicationContext.getBean(SqlSessionTemplate.class);
        Role role = new Role();
        role.setRoleName("Roger");
        role.setNote("Roger_JX");
        sqlSessionTemplate.insert("com.ssm.chapter10.sql.mapper.RoleMapper.insertRole", role);
        Long id = role.getId();
        sqlSessionTemplate.selectOne("com.ssm.chapter10.sql.mapper.RoleMapper.getRole", id);
        role.setNote("setNote");
        sqlSessionTemplate.update("com.ssm.chapter10.sql.mapper.RoleMapper.updateRole", role);
        sqlSessionTemplate.delete("com.ssm.chapter10.sql.mapper.RoleMapper.deleteRole", id);
    }

    @Test
    public void test5() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config-mybatis.xml");
        RoleMapper roleMapper = applicationContext.getBean(RoleMapper.class);
        Role role = roleMapper.getRole(3L);
        System.out.println(role.getRoleName());
    }

    @Test
    public void test6() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config-mybatis.xml");
        RoleMapper roleMapper = applicationContext.getBean(RoleMapper.class);
        Role role = new Role();
        role.setRoleName("One");
        role.setNote("Note_One");
        roleMapper.insertRole(role);
        Long id = role.getId();
        roleMapper.getRole(id);
        role.setNote("Update: Note_One");
        roleMapper.updateRole(role);
        roleMapper.deleteRole(id);
    }

    @Test
    public void test7() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config-mybatis.xml");
        JdbcTemplate jdbcTemplate = applicationContext.getBean(JdbcTemplate.class);
        TransactionDefinition def = new DefaultTransactionAttribute();

        // The fact fetch is "org.springframework.jdbc.datasource.DataSourceTransactionManager"
        PlatformTransactionManager transactionManager = applicationContext.getBean(PlatformTransactionManager.class);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            jdbcTemplate.update("insert into t_role(role_name, note) values ('Yes', 'yep')");
            transactionManager.commit(status);
        } catch (Throwable e) {
            transactionManager.rollback(status);
        }
    }

    /* ########################################################################################################## */
    public static int insertRole(JdbcTemplate jdbcTemplate, Role role) {
        return jdbcTemplate.update("insert into t_role(role_name, note) values (?, ?)", role.getRoleName(), role.getNote());
    }

    public static int deleteRole(JdbcTemplate jdbcTemplate, Long id) {
        return jdbcTemplate.update("delete from t_role where id = ?", id);
    }

    public static List<Role> findRole(JdbcTemplate jdbcTemplate, String roleName) {
        String sql = "select id, role_name, note from t_role where role_name like concat('%', ?, '%')";
        Object[] params = {roleName};
        List<Role> list = jdbcTemplate.query(sql, params, (ResultSet rs, int rowNum) -> {
            Role result = new Role();
            result.setId(rs.getLong("id"));
            result.setRoleName(rs.getString("role_name"));
            result.setNote(rs.getString("note"));
            return result;
        });
        return list;
    }

    public static Role getRoleByConnectionCallback(JdbcTemplate jdbcTemplate, Long id) {
        Role role;
        role = jdbcTemplate.execute((Connection con) -> {
            Role result = null;
            String sql = "select id, role_name, note from t_role where id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = new Role();
                result.setId(rs.getLong("id"));
                result.setRoleName(rs.getString("role_name"));
                result.setNote(rs.getString("note"));
            }
            return result;
        });
        return role;
    }

    public static Role getRoleByStatementCallback(JdbcTemplate jdbcTemplate, Long id) {
        Role role;

        role = jdbcTemplate.execute(new StatementCallback<Role>() {
            @Override
            public Role doInStatement(Statement stmt) throws SQLException, DataAccessException {
                Role result = null;
                String sql = "select * from t_role where id = " + id;
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    result = new Role();
                    result.setId(rs.getLong("id"));
                    result.setRoleName(rs.getString("role_name"));
                    result.setNote(rs.getString("note"));
                }
                return result;
            }
        });
        return role;
    }
}
