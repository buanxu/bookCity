package com.bookcity.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcUtils {

    private static ComboPooledDataSource dataSource;
    static{
        //创建ComboPooledDataSource时默认去类路径下加载c3p0-config.xml
        dataSource=new ComboPooledDataSource();
        try {
            dataSource.setDriverClass("com.mysql.jdbc.Driver");
            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/book");
            dataSource.setUser("root");
            dataSource.setPassword("root");
            dataSource.setInitialPoolSize(5);
            dataSource.setMaxPoolSize(10);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }

    }

    /**
     * 从连接池获取链接，返回null说明获取连接失败
     * @return
     */
    public static Connection getConnection(){
        Connection connection=null;
        try {
            connection=dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  connection;
    }

    /**
     * 释放连接，把链接放回连接池
     * @param connection
     */
    public static void close(Connection connection){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
