package com.bookcity.test;

import com.bookcity.entity.User;
import com.bookcity.service.UserService;
import com.bookcity.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {

    private UserService userService=new UserServiceImpl();

    /**
     * 由于filter加了事务，所以只有通过浏览器添加数据的时候才能成功保存在数据库里，在测试里没有触发事务，所以添加数据不会成功
     */
    @Test
    public void register() {
        userService.register(new User(null,"漆锣淼","123456","67438@126.com"));
    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null,"漆锣淼","1283456","67438@126.com")));
    }

    @Test
    public void existsUsername() {
        System.out.println(userService.existsUsername("xuyan"));
    }
}