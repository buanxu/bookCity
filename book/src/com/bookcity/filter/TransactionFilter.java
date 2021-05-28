package com.bookcity.filter;

import com.bookcity.utils.JdbcUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * 使用Filter统一给所有service的方法都加上try-catch来管理事务
 */
public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        try {
            //filterChain要么调用下一个filter，要么调用目标资源即servlet程序，
            // 而servlet程序又会调用service中的方法，所以filterChain间接调用了service中的方法
            //所以对filterChain.doFilter捕获异常
            filterChain.doFilter(servletRequest,servletResponse);

            //提交事务
            JdbcUtils.commitAndClose();
        } catch (Exception e) {
            e.printStackTrace();
            //回滚事务
            JdbcUtils.rollbackAndClose();

            //再把异常抛给tomcat服务器
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {

    }
}
