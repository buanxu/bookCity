package com.bookcity.test;

import com.bookcity.entity.User;
import com.bookcity.service.UserService;
import com.bookcity.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {

    private UserService userService=new UserServiceImpl();

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
        System.out.println(userService.existsUsername("徐艳"));
    }
}