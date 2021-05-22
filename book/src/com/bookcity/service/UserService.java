package com.bookcity.service;

import com.bookcity.entity.User;

public interface UserService {

    /**
     * 注册
     * @param user
     */
    public void register(User user);

    /**
     * 登录
     * @param user
     * @return
     */
    public User login(User user);

    /**
     * 判断用户名是否存在
     * @param username
     * @return
     */
    public boolean existsUsername(String username);
}


