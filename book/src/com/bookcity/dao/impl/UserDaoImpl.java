package com.bookcity.dao.impl;

import com.bookcity.dao.UserDao;
import com.bookcity.entity.User;

public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User findByUsername(String username) {
        String sql="SELECT * FROM t_user where username=?";
        User user = new User();
        user=findOne(User.class, sql, username);
        return user;
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        String sql="SELECT * FROM t_user where username=? and password=?";
        return findOne(User.class, sql, username,password);
    }

    @Override
    public int save(User user) {
        String sql="INSERT INTO t_user( username, password, email) VALUES (?,?,?)";
        return update(sql, user.getUsername(),user.getPassword(),user.getEmail());
    }
}
