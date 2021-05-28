package com.bookcity.dao.impl;

import com.bookcity.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao {
    private QueryRunner query=new QueryRunner();

    /**
     * QueryRunner操作数据库
     * 增删改
     * @param sql
     * @param params
     * @return
     */
    public int update(String sql,Object ...params){
        Connection connection= JdbcUtils.getConnection();

        try {
            return query.update(connection, sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询一条记录
     * @param type
     * @param sql
     * @param params
     * @param <T>
     * @return
     */
    public <T> T findOne(Class<T> type,String sql,Object ...params){
        Connection connection=JdbcUtils.getConnection();

        try {
            return query.query(connection, sql, new BeanHandler<T>(type),params );
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询一个List
     * @param type
     * @param sql
     * @param params
     * @param <T>
     * @return
     */
    public <T> List<T> findList(Class<T> type,String sql,Object ...params){
        Connection connection=JdbcUtils.getConnection();
        try {
            return query.query(connection, sql, new BeanListHandler<>(type),params );
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询返回一行一列
     * @param sql
     * @param params
     * @return
     */
    public Object findSingleValue(String sql,Object ...params){
        Connection connection=JdbcUtils.getConnection();

        try {
            return query.query(connection,sql,new ScalarHandler(),params);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
