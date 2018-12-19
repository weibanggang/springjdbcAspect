package com.wbg.springaopdatasource.jdbc;


import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ConnM {
    public static ThreadLocal<Connection> threadLocal = null;
    public static ThreadLocal<ResultSet> rs = new ThreadLocal<>();
    public static Connection getConnection(){
        return threadLocal.get();
    }
    public static ResultSet get() throws SQLException {
        threadLocal.get().prepareStatement("select * from role");
        return rs.get();
    }
}
