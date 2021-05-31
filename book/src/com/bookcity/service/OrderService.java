package com.bookcity.service;

import com.bookcity.entity.Cart;
import com.bookcity.entity.Order;
import com.bookcity.entity.OrderItem;
import com.bookcity.entity.UserOrder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface OrderService {
    /**
     *  创建订单
     * @param cart
     * @param userId
     */
    public String createOrder(Cart cart,Integer userId);


    /**
     * 查询订单信息
     * @param userId
     * @return
     */
    public List<Order> findOrder(Integer userId);

    /**
     * 查询订单中的所有商品的信息
     * @param orderId
     * @return
     */
    public List<OrderItem> findOrderItem(String orderId);

    /**
     * 查询任意一个订单
     * @param orderId
     * @return
     */
    public UserOrder findOneUserOrder(String orderId);


    /**
     * 后台管理员在查询订单的时候，订单中要包括用户名和用户订单
     */
    public List<UserOrder> findUserOrders(String username);


    /**
     * 在发货后或签收后修改订单
     * @param orderId
     * @param operate
     */
    public void updateOrderStatus(String orderId,String operate);
}
