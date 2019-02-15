import com.ssm.chapter10.annotation.config.ApplicationConfig;
import com.ssm.chapter10.annotation.controller.RoleController;
import com.ssm.chapter10.annotation.controller.Util;
import com.ssm.chapter10.annotation.pojo.PojoConfig;
import com.ssm.chapter10.annotation.service.RoleDataSourceService;
import com.ssm.chapter10.annotation.service.RoleService;
import com.ssm.chapter10.annotation.service.RoleService2;
import com.ssm.chapter10.el.pojo.ElBean;
import com.ssm.chapter10.el.pojo.Number;
import com.ssm.chapter10.pojo.Role;
import com.ssm.chapter10.pojo.UserRoleAssembly;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProjectTest {

    @Test
    public void get() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Role role = (Role) applicationContext.getBean("com.ssm.chapter10.pojo.Role#0");
        System.out.println(role.getRoleName() + ", " + role.getNote());
    }

    @Test
    public void test1() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserRoleAssembly assembly = (UserRoleAssembly) applicationContext.getBean("userRoleAssembly");
        System.err.println(assembly.getList());
        System.err.println(assembly.getMap());
        System.err.println(assembly.getSet());
    }

    @Test
    public void testAnnotationAssembly() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(PojoConfig.class);
        com.ssm.chapter10.annotation.pojo.Role role = applicationContext.getBean(
                com.ssm.chapter10.annotation.pojo.Role.class
        );
        System.err.println(role.getRoleName());
    }

    @Test
    public void testAnnotationAssembly2() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        com.ssm.chapter10.annotation.pojo.Role role = applicationContext.getBean(com.ssm.chapter10.annotation.pojo.Role.class);
        RoleService roleService = applicationContext.getBean(RoleService.class);
        roleService.printRoleInfo(role);
        applicationContext.close();
    }

    @Test
    public void testAutoWired() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        RoleService2 roleServiceImpl2 = applicationContext.getBean(RoleService2.class);
        roleServiceImpl2.printRoleInfo();
    }

    @Test
    public void testAutoWired2() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        RoleController roleController = applicationContext.getBean(RoleController.class);
        com.ssm.chapter10.annotation.pojo.Role role = applicationContext.getBean(com.ssm.chapter10.annotation.pojo.Role.class);
        roleController.printRole(role);
    }

    @Test
    public void testBeanAnnotation() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        Util util = (Util) applicationContext.getBean("util");
        System.err.println("------------------------------------------------------------------------------");
        System.err.println(util.getProp1());
        applicationContext.close();
    }

    @Test
    public void testDataSource() {
//        AnnotationConfigApplicationContext applicationContext= new AnnotationConfigApplicationContext(ApplicationConfig.class);
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        RoleDataSourceService sourceService = applicationContext.getBean(RoleDataSourceService.class);
        com.ssm.chapter10.annotation.pojo.Role role = sourceService.getRole(1L);
        System.err.println("------------------------------------------------------------------------------------------");
        System.err.println(role.getRoleName());
    }

    @Test
    public void testProperties() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        String url = applicationContext.getEnvironment().getProperty("jdbc.database.url");
        System.err.println("---------------------------------jdbc.database.url-------------------------------------");
        System.err.println(url);
    }

    @Test
    public void testProperties2() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        DataSource dataSource = (DataSource) applicationContext.getBean("dataSource2");
    }

    @Test    //Test for create dataSource3 bean
    public void testProperties3() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        DataSource dataSource = (DataSource) applicationContext.getBean("dataSource3");
        System.err.println("------------------------------------------------------------------------------------");
        System.err.println(dataSource == null);
    }

    @Test
    public void singletonTest() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Role role = (Role) applicationContext.getBean("role1");
        Role role1 = (Role) applicationContext.getBean("role1");
        System.err.println("If role == role1: " + (role == role1));
    }

    @Test
    public void springELTest() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("'hello world'");
        String str = (String) exp.getValue();
        System.out.println(str);

        exp = parser.parseExpression("'hello world'.charAt(0)");
        char ch = (char) exp.getValue();
        System.out.println(ch);

        exp = parser.parseExpression("'hello world'.bytes");
        byte[] bytes = (byte[]) exp.getValue();
        System.out.println("bytes[]: " + Arrays.toString(bytes));

        exp = parser.parseExpression("'hello world'.bytes.length");
        int length = (int) exp.getValue();
        System.out.println("length is: " + length);

        exp = parser.parseExpression("new String('abc')");
        String abc = (String) exp.getValue();
        System.err.println("abc is: " + abc);
    }

    @Test
    public void testSpringEL2() {
        ExpressionParser parser = new SpelExpressionParser();
        Role role = new Role(1L, "role_name_ABC", "note_abc");

        Expression exp = parser.parseExpression("note");

        String note = (String) exp.getValue(role);
        System.out.println(note);


        EvaluationContext ctx = new StandardEvaluationContext(role);
        parser.parseExpression("note").setValue(ctx, "new_note");
        note = parser.parseExpression("note").getValue(ctx, String.class);
        System.out.println(note);

        String roleName = parser.parseExpression("getRoleName()").getValue(ctx, String.class);
        System.out.println(roleName);

        List<String> list = new ArrayList<>();
        list.add("value1");
        list.add("value2");

        ctx.setVariable("list", list);
        parser.parseExpression("#list[1]").setValue(ctx, "update_value2");
        System.out.println(parser.parseExpression("#list[1]").getValue(ctx));
    }

    @Test
    public void testSpringEL3() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        ElBean bean = applicationContext.getBean(ElBean.class);
        System.err.println(bean.getRole().getNote());
        System.err.println(bean.getRole().getId());
        System.err.println(bean.getRole().getRoleName());
    }

    @Test  // 引用靜態變量
    public void testSpringEL4() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("T(Math).PI");
      double result = (double) exp.getValue();
        System.out.println("PI is: " + result);

//        exp = parser.parseExpression("'aaa'.equals('aaa1')");
//        System.err.println(exp.getValue());
    }

    @Test
    public void testSpringEL5() {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        Number number = context.getBean(Number.class);
        System.err.println(number.getPI());
    }
}