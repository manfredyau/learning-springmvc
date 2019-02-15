package com.ssm.chapter10.annotation.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class DataSourceConditional implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 獲取上下文環境
        Environment env = context.getEnvironment();
        System.err.println("--------------------------------metadata----------------------------------------------");
        System.err.println(metadata);
        String property = env.getProperty("jdbc.database.driver");
        String property1 = env.getProperty("jdbc.database.url");
        String property2 = env.getProperty("jdbc.database.username");
        String property3 = env.getProperty("jdbc.database.password");
        //判斷是否存在關於數據源的基礎配置
        return env.containsProperty("jdbc.database.driver")
                && env.containsProperty("jdbc.database.url")
                && env.containsProperty("jdbc.database.username")
                && env.containsProperty("jdbc.database.password");
    }
}
