package com.bookcity.web;

import com.bookcity.entity.User;
import com.bookcity.service.UserService;
import com.bookcity.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginController extends HttpServlet {

    private UserService userService=new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User loginUser = userService.login(new User(null, username, password, null));
        if (loginUser==null){//登陆失败
            req.setAttribute("errorMsg", "用户名或密码错误！");
            req.setAttribute("username",username );
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req ,resp);
        }else {//成功
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req ,resp);
        }
    }
}
