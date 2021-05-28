package com.bookcity.web;

import com.bookcity.filter.TransactionFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public abstract class BaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //解决post请求中文乱码问题
        //要在获取参数之前设置编码
        resp.setCharacterEncoding("utf-8");
        //解决响应时中文乱码
        resp.setContentType("html/text; charset=utf-8");

        String action=req.getParameter("action");
        //获取到要调用的方法名来反射对象调用相应方法
        try {
            Method method=this.getClass().getDeclaredMethod(action, HttpServletRequest.class,HttpServletResponse.class);
            method.invoke(this, req,resp);
        } catch (Exception e) {
            e.printStackTrace();
            //虽然BaseServlet捕获到了异常，但是还要再把异常抛给TransactionFilter，
            // 以便TransactionFilter能捕获到异常从而回滚事务，如果BaseServlet不把异常抛给TransactionFilter，
            // 则TransactionFilter就无法捕捉异常，造成提交事务而不是回滚事务
            throw new RuntimeException(e);
        }

    }
}
