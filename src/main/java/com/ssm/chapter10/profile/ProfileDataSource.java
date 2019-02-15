package com.ssm.chapter10.profile;

import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Properties;

@Component
public class ProfileDataSource {

    @Bean(name = "devDataSource")
    @Profile("dev")
    public DataSource getDevDataSource() {
        Properties props = new Properties();
        props.setProperty("driver", "com.mysql.cj.jdbc.Driver");
        props.setProperty("url", "jdbc:mysql://localhost:3306/chapter6?serverTimezone=Asia/Shanghai");
        props.setProperty("username", "root");
        props.setProperty("password", "root");
        DataSource dataSource = null;
        try {
            dataSource = BasicDataSourceFactory.createDataSource(props);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataSource;
    }
    @Bean(name = "testDataSource")
    @Profile("test")
    public DataSource getTestDataSource() {
        Properties props = new Properties();
        props.setProperty("driver", "com.mysql.cj.jdbc.Driver");
        props.setProperty("url", "jdbc:mysql://localhost:3306/chapter7?serverTimezone=Asia/Shanghai");
        props.setProperty("username", "root");
        props.setProperty("password", "root");
        DataSource dataSource = null;
        try {
            dataSource = BasicDataSourceFactory.createDataSource(props);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataSource;
    }

}
