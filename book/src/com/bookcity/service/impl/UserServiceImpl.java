package com.bookcity.service.impl;

import com.bookcity.dao.UserDao;
import com.bookcity.dao.impl.UserDaoImpl;
import com.bookcity.entity.User;
import com.bookcity.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao=new UserDaoImpl();

    @Override
    public void register(User user) {
        userDao.save(user);
    }

    @Override
    public User login(User user) {
        return userDao.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {
        //用户名不存在返回false
        if (userDao.findByUsername(username)==null){
            return false;
        }
        return  true;
    }
}
