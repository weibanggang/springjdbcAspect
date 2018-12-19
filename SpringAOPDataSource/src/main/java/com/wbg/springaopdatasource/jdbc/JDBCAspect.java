package com.wbg.springaopdatasource.jdbc;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Aspect
public class JDBCAspect {

    @Around("execution(* com.wbg.springaopdatasource.jdbc.RoleDAO.*(..))")
    public Object getCon(ProceedingJoinPoint pj) throws Throwable {
        Connection connection = null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/wbg_logistics",
                    "root",
                    "123456"
            );
            connection.setAutoCommit(false);//将事务关闭自动提交

            ConnM.threadLocal = new ThreadLocal<>();
            ConnM.threadLocal.set(connection);

            Object o = pj.proceed();
            connection.commit();//提交事务
            return o;
        } catch (ClassNotFoundException e) {
            System.out.println("驱动错误："+e.getMessage());
            throw e;
        } catch (SQLException e){
            System.out.println("连接数据库出错："+e.getMessage());
            throw e;
        }
        catch (Throwable throwable) {
            if (connection != null){
                try {
                    connection.rollback();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("错误信息Throwable："+throwable.getMessage());
            throw throwable;
        }finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
