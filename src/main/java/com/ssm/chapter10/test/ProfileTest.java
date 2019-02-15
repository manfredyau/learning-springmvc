package com.ssm.chapter10.test;

import com.ssm.chapter10.profile.ProfileDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ProfileDataSource.class)
//@ActiveProfiles("dev")
public class ProfileTest {
    @Autowired
    private DataSource dataSource;

    @Test
    public void test() {
        System.err.println(dataSource.getClass().getName());
    }
}
