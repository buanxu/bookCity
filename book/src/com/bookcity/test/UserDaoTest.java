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

    /**
     * 由于filter加了事务，所以只有通过浏览器添加数据的时候才能成功保存在数据库里，在测试里没有触发事务，所以添加数据不会成功
     */
    @Test
    public void save() {
        UserDao userDao = new UserDaoImpl();
        System.out.println(userDao.save(new User(null,"南军","123456","67438@126.com")));
    }
}