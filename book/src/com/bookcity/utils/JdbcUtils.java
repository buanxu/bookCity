package com.bookcity.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcUtils {

    private static ComboPooledDataSource dataSource;
    //给当前线程绑定一个connection连接
    private static ThreadLocal<Connection> threadLocal=new ThreadLocal<Connection>();

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
     * 给当前线程绑定一个连接，确保每次都获取的是同一个连接，从而保证事务
     * @return
     */
    public static Connection getConnection(){
        Connection connection=threadLocal.get();

        //第一次获取连接时由于线程上还没绑定连接，所以获取的连接是空的，故要绑定连接
        if (connection==null){
            try {
                //从连接池获取连接
                connection=dataSource.getConnection();
                //给当前前程绑定链接
                threadLocal.set(connection);
                //设置手动提交事务
                connection.setAutoCommit(false);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return  connection;
    }

    /**
     * 提交事务并关闭连接
     */
    public static void commitAndClose(){
        Connection connection=threadLocal.get();
        if (connection!=null){
            try {
                //提交事务
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    //关闭连接
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        // 一定要执行remove操作，否则就会出错。（因为Tomcat服务器底层使用了线程池技术）
        threadLocal.remove();
    }

    /**
     * 回滚事务并关闭连接
     */
    public static void rollbackAndClose(){
        Connection connection=threadLocal.get();
        if (connection!=null){
            try {
                //回滚事务
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    //关闭事务
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        // 一定要执行remove操作，否则就会出错。（因为Tomcat服务器底层使用了线程池技术）
        threadLocal.remove();
    }

    /**
     * 释放连接，把链接放回连接池
     * @param connection
     */
    /*public static void close(Connection connection){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
}
