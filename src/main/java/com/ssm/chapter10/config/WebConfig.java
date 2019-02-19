package com.ssm.chapter10.config;

import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan("com.*")
//啟用 Spring Web MVC
@EnableWebMvc
public class WebConfig {
    private DataSource dataSource;

    /*--------------------------------------------- 自定義的 Beans -------------------------------------------------*/
    @Bean("viewResolver")
    public ViewResolver initViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean
    public MapperScannerConfigurer getMapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.ssm.chapter10.sql.mapper");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setAnnotationClass(Repository.class);
        return mapperScannerConfigurer;
    }

    @Bean(name = "dataSource")
    public DataSource initDataSource() {
        if (dataSource != null) {
            return dataSource;
        }
        Properties props = new Properties();
        props.setProperty("driverClassName", "com.mysql.jdbc.Driver");
        props.setProperty("url", "jdbc:mysql://localhost:3306/chapter6?serverTimezone=Asia/Shanghai");
        props.setProperty("username", "root");
        props.setProperty("password", "root");
        props.setProperty("maxActive", "200");
        props.setProperty("maxIdle", "20");
        props.setProperty("maxWait", "30000");
        try {
            dataSource = BasicDataSourceFactory.createDataSource(props);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    @Bean("sqlSessionFactory")
    public SqlSessionFactoryBean getSqlSessionFactoryBean() {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(initDataSource());
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("sqlMapConfig.xml"));
        return sqlSessionFactoryBean;
    }
}
