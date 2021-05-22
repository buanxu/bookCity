package com.bookcity.test;

import com.bookcity.dao.UserDao;
import com.bookcity.dao.impl.UserDaoImpl;
import com.bookcity.entity.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoTest {

    @Test
    public void findByUsername() {
        UserDao userDao = new UserDaoImpl();
        System.out.println(userDao.findByUsername("南军"));
    }

    @Test
    public void findByUsernameAndPassword() {
        UserDao userDao = new UserDaoImpl();
        System.out.println(userDao.findByUsernameAndPassword("南军","123456"));
    }

    @Test
    public void save() {
        UserDao userDao = new UserDaoImpl();
        System.out.println(userDao.save(new User(null,"李雨谋","123456","67438@126.com")));
    }
}