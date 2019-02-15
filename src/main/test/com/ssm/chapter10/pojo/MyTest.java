package com.ssm.chapter10.pojo;
import com.ssm.chapter10.annotation.config.ApplicationConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    @Test
    public void get() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Role role = (Role) applicationContext.getBean("role1");
        System.out.println(role.getRoleName() + ", " + role.getNote());
    }

    @Test
    public void test() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        com.ssm.chapter10.mine.Role role = (com.ssm.chapter10.mine.Role) applicationContext.getBean("myrole");
        role.printMoney();
    }
}
