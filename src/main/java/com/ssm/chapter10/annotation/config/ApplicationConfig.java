package com.ssm.chapter10.annotation.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@ComponentScan(basePackages = {"com.ssm.chapter10"})
@ImportResource({"classpath:applicationContext.xml"})
@PropertySource(value = {"classpath:database-config.properties"}, ignoreResourceNotFound = true)
public class ApplicationConfig {
    /*@Bean(name = "util", initMethod = "init", destroyMethod = "myDestroy")
    public Util initUtil() {
        Util util = new Util();
        util.setProp1("屬性1");
        util.setProp2("屬性2");
        return util;
    }*/
   /* @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }*/
}
