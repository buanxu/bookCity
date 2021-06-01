package com.bookcity.service;

import com.bookcity.entity.*;

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
     * 查询订单状态
     * @param orderId
     * @return
     */
    public Integer findOrderStatus(String orderId);

    /**
     * 在发货后或签收后修改订单
     * @param orderId
     * @param operate
     */
    public void updateOrderStatus(String orderId,String operate);


    /**
     * 对后台订单进行分页
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Page<UserOrder> page(int pageNo, int pageSize);
}
