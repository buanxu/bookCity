package com.bookcity.web;

import com.bookcity.entity.User;
import com.bookcity.service.UserService;
import com.bookcity.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterController extends HttpServlet {

    private UserService userService=new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path=req.getScheme() + "://" +req.getServerName() + ":" +req.getServerPort() +req.getContextPath() +"/";

        //获取参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        //检查验证码
        if("6n6np".equalsIgnoreCase(code)){//验证码正确
            //再检查用户名是否已存在
            if (userService.existsUsername(username)){//已存在，则跳转到注册页面
                System.out.println("用户名["+username+"]已存在");

                //返回给前台信息
                req.setAttribute("errorMsg", "该用户名已存在");
                req.setAttribute("username",username);
                req.setAttribute("email",email);

                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            }else{//不存在,则注册并跳转到登陆页面
                userService.register(new User(null,username,password,email));
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
            }
        }else {//验证码错误
            System.out.println("验证码错误");

            //返回给前台信息
            req.setAttribute("errorMsg", "验证码错误");
            req.setAttribute("username",username);
            req.setAttribute("email",email);
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }

    }
}
