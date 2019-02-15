package com.ssm.chapter10.annotation.config;

import com.ssm.chapter10.annotation.condition.DataSourceConditional;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Properties;

@Component
public class DataSourceBean {

    @Value("${jdbc.database.driver}")
    private String driver;

    @Value("${jdbc.database.url}")
    private String url;

    @Value("${jdbc.database.username}")
    private String username;

    @Value("${jdbc.database.password}")
    private String password;

    @Bean(name = "dataSource2")
    public DataSource getDatasource() {
        Properties props = new Properties();
        props.setProperty("driver", driver);
        props.setProperty("url", url);
        props.setProperty("username", username);
        props.setProperty("password", password);
        DataSource dataSource = null;
        try {
            dataSource = BasicDataSourceFactory.createDataSource(props);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.err.println("----------------------properties-------------------------");
        System.err.println(props);
        return dataSource;
    }

    @Bean(name = "dataSource3")
    @Conditional({DataSourceConditional.class})
    public DataSource getDataSource(
            @Value("${jdbc.database.driver}") String driver,
            @Value("${jdbc.database.url}") String url,
            @Value("${jdbc.database.username}") String username,
            @Value("${jdbc.database.password}") String password
    ) {
        Properties props = new Properties();
        props.setProperty("driver", driver);
        props.setProperty("url", url);
        props.setProperty("username", username);
        props.setProperty("password", password);
        DataSource dataSource = null;
        try {
            dataSource = BasicDataSourceFactory.createDataSource(props);
        } catch (Throwable t) {
            t.printStackTrace();
        }

        return dataSource;
    }
}
