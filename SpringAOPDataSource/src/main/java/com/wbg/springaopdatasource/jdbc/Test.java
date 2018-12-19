package com.wbg.springaopdatasource.jdbc;

import com.wbg.springaopdatasource.entity.Role;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws SQLException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DataBaseConfig.class);
        RoleDAO roleDAO = (RoleDAO) applicationContext.getBean(RoleDAO.class);
    roleDAO.save(new Role(1, "15", "5"));
    }
}
