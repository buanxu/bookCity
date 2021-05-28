package com.bookcity.web;

import com.bookcity.entity.Cart;
import com.bookcity.entity.User;
import com.bookcity.service.BookService;
import com.bookcity.service.OrderService;
import com.bookcity.service.UserService;
import com.bookcity.service.impl.BookServiceImpl;
import com.bookcity.service.impl.OrderServiceImpl;
import com.bookcity.service.impl.UserServiceImpl;
import com.bookcity.utils.JdbcUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderController extends BaseServlet {
    private OrderService orderService=new OrderServiceImpl();
    private BookService bookService=new BookServiceImpl();
    private UserService userService=new UserServiceImpl();

    /**
     * 结账时创建订单
     * @param req
     * @param resp
     */
    public void createOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        //从session中获取购物车信息
        Cart cart=(Cart) req.getSession().getAttribute("cart");
        //从session中获取用户信息
        User loginUser=(User) req.getSession().getAttribute("user");

        //结账之前先判断是否登录了，没登陆先去登录
        if (loginUser==null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            //转发或者重定向之后，如果不需要执行后面的代码的话就加return;
            return;
        }

        //调用service创建订单，并获取订单表编号
        String orderId = orderService.createOrder(cart, loginUser.getId());

        //把订单id放到session中，以便重定向后在jsp仍能获取到订单id
        req.getSession().setAttribute("orderId", orderId);

        //结完账重定向到带有订单编号的页面，重定向是为了防止重复提交
        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }
}
