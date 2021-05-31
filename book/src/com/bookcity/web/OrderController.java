package com.bookcity.web;

import com.bookcity.entity.*;
import com.bookcity.service.BookService;
import com.bookcity.service.OrderService;
import com.bookcity.service.UserService;
import com.bookcity.service.impl.BookServiceImpl;
import com.bookcity.service.impl.OrderServiceImpl;
import com.bookcity.service.impl.UserServiceImpl;
import com.bookcity.utils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * 查询订单信息
     * @param req
     * @param resp
     */
    public void findOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //先获取用户id
        Integer userId = BeanUtils.parseInt(req.getParameter("userId"),0);
        //查出用户的所有订单
        List<Order> orders = orderService.findOrder(userId);
        //把order放到request域
        req.setAttribute("orders",orders);
        //转发到订单页面
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req, resp);
    }

    /**
     * 查询按钮的功能
     * 后台管理员查询某用户的所有订单
     * @param req
     * @param resp
     */
    public void findUserOrdersByUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取用户名
        String username = req.getParameter("username");
        List<UserOrder> userOrders = orderService.findUserOrders(username);
        req.setAttribute("userOrders",userOrders);
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req, resp);
    }

    /**
     * 查询按钮的功能
     * 后台管理员根据订单号查询某一订单
     * @param req
     * @param resp
     */
    public void findOrderByOrderId(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取订单id
        String orderId = req.getParameter("orderId");
        //查询某一订单
        UserOrder userOrder = orderService.findOneUserOrder(orderId);
        //把查询出来的UserOrder封装在一个List<UserOrder>，因为前台页面输出订单时是遍历输出的
        List<UserOrder> userOrders=new ArrayList<UserOrder>();
        userOrders.add(userOrder);
        //将userOrders存入request域
        req.setAttribute("userOrders",userOrders);
        //转发到后台的订单管理页面
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req, resp);
    }

    /**
     * 跳转到后台的订单管理页面时，订单中要包括用户名和用户订单
     * @param req
     * @param resp
     */
    public void findAllUserOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserOrder> userOrders = orderService.findUserOrders(null);
        req.setAttribute("userOrders",userOrders);
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req, resp);
    }

    /**
     * 查询订单项详细信息
     * @param req
     * @param resp
     */
    public void findOrderItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取订单id
        String orderId = req.getParameter("orderId");
        //查询订单中的所有订单项
        List<OrderItem> orderItems = orderService.findOrderItem(orderId);
        //把订单项存进request域
        req.setAttribute("orderItems",orderItems );
        //转发到订单项页面
        req.getRequestDispatcher("/pages/order/orderItem.jsp").forward(req, resp);
    }

    /**
     *发货后或签收订单后修改订单状态
     * @param req
     * @param resp
     */
    public void updateOrderStatus(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //先清除之前的订单
        req.removeAttribute("orders");
        //获取订单所属的用户的id
        Integer userId = BeanUtils.parseInt(req.getParameter("userId"), 0);
        //获取订单编号
        String orderId = req.getParameter("orderId");
        //获取订单操作，是签收了还是发货了
        String operate = req.getParameter("operate");
        //修改订单状态
        orderService.updateOrderStatus(orderId, operate);
        //再查询用户的所有订单其中包含了已修改了订单状态的订单
        List<Order> orders = orderService.findOrder(userId);
        //把orders放到request域
        req.setAttribute("orders",orders);
        //转发到订单页面
        req.getRequestDispatcher("/pages/order/order_manager.jsp").forward(req, resp);

    }
}
