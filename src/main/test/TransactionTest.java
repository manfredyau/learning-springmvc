import com.ssm.chapter10.controller.RoleController;
import com.ssm.chapter10.pojo.Role;
import com.ssm.chapter10.service.RoleListService;
import com.ssm.chapter10.service.RoleService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class TransactionTest {
    @Test
    public void test1() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config-mybatis.xml");
        RoleListService roleListService = applicationContext.getBean(RoleListService.class);
//        RoleListServiceImpl roleListService = applicationContext.getBean(RoleListServiceImpl.class);
        List<Role> roleList = new ArrayList<>();
        for (int i = 1; i <= 2; i++) {
            Role role = new Role();
            role.setRoleName("role_name_" + i);
            role.setNote("role_note_" + i);
            roleList.add(role);
        }
        int count = roleListService.insertRoleList(roleList);
        System.err.println(count);
    }

    @Test
    public void test2() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config-mybatis.xml");
        RoleService roleService = applicationContext.getBean(RoleService.class);
        List<Role> roleList = new ArrayList<>();
        for (int i = 1; i <= 2; i++) {
            Role role = new Role();
            role.setRoleName("role_name_" + i);
            role.setNote("role_note_" + i);
            roleList.add(role);
        }
        int count = roleService.insertRoleList(roleList);
        System.err.println(count);
    }

    @Test
    /*
      13.8.1錯誤使用Service
     */
    public void test3() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config-mybatis.xml");
        RoleController roleController = applicationContext.getBean(RoleController.class);
        roleController.errorUseServices();
        RoleService roleService = applicationContext.getBean(RoleService.class);
       /* RoleListService roleListService = applicationContext.getBean(RoleListService.class);
        ArrayList<Role> roles = new ArrayList<>();
        for (int i = 1; i <= 2; i++) {
            Role role = new Role();
            role.setRoleName("role" + i);
            role.setNote("note" + i);
            roles.add(role);
        }
        roleListService.insertRoleList(roles);*/
    }
}
