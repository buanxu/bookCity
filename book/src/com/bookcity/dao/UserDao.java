package com.bookcity.dao;

import com.bookcity.entity.User;

public interface UserDao {

    /**
     * 通过用户名查询
     * @param username
     * @return
     */
    public User findByUsername(String username);

    /**
     * 通过用户名和密码查询
     * @param username
     * @param password
     * @return
     */
    public User findByUsernameAndPassword(String username,String password);

    /**
     * 保存用户
     * @param user
     * @return
     */
    public int save(User user);
}
