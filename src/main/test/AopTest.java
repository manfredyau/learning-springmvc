import com.ssm.chapter10.aop.congig.AopConfig;
import com.ssm.chapter10.aop.service.RoleService;
import com.ssm.chapter10.aop.verifier.RoleVerifier;
import com.ssm.chapter10.multi.bean.MultiBean;
import com.ssm.chapter10.pojo.Role;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopTest {
    @Test
    public void aopTest1() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AopConfig.class);

        RoleService roleService = applicationContext.getBean(RoleService.class);
        Role role = new Role();
        role.setId(1L);
        role.setRoleName("Manfred");
        role.setNote("note");
        roleService.printRole(role, 3);
        System.out.println("################################################################");
        role = null;
        roleService.printRole(role, 4);
    }

    @Test
    public void aopTest2() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config-aoptest.xml");
        RoleService roleService = applicationContext.getBean(RoleService.class);
        Role role = new Role();
        role.setId(1L);
        role.setRoleName("Manfred");
        role.setNote("Hello");
        roleService.printRole(role, 3);
        System.err.println("###################################");
        role = null;
        roleService.printRole(role, 4);
    }

    @Test
    public void aopTest3() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config-aoptest.xml");
        RoleService roleService = applicationContext.getBean(RoleService.class);
        Role role = new Role();
        role.setId(1L);
        role.setRoleName("Manfred");
        role.setNote("Hello");
        RoleVerifier verifier = (RoleVerifier) roleService;
        if (verifier.verify(role)) {
            roleService.printRole(role, 10101);
        }
    }

    @Test
    public void aopTest4() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config-aoptest2.xml");
        com.ssm.chapter10.xml.service.RoleService roleService = applicationContext.getBean(com.ssm.chapter10.xml.service.RoleService.class);
        Role role = new Role();
        role.setId(3L);
        role.setRoleName("Manfred");
        role.setNote("Note");
        if (((RoleVerifier) roleService).verify(role)) {
            roleService.printRole(role);
        }
    }

    @Test
    public void aopTest5() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config-aoptest2.xml");
        Role role = new Role();
        role.setId(30L);
        role.setRoleName("Kotlin");
        role.setNote("Java");
        com.ssm.chapter10.game.service.RoleService roleService = (com.ssm.chapter10.game.service.RoleService) applicationContext.getBean("roleService_my");
        roleService.printRole(role);
    }

    @Test
    public void aopTest6() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config-aoptest2.xml");
        MultiBean multiBean = applicationContext.getBean(MultiBean.class);
        multiBean.testMulti();
    }
}
