package com.bookcity.dao;

import com.bookcity.entity.Order;
import com.bookcity.entity.UserOrder;

import java.util.List;

public interface OrderDao {

    /**
     * 要添加订单，则此订单对应的用户id即userId必须存在，所以添加了外键
     * @param order
     * @return
     */
    public int save(Order order);

    /**
     * 查询用户的订单
     * @param userId
     * @return
     */
    public List<Order>  findOrder(Integer userId);

    /**
     * 查询任意一个订单
     * @param orderId
     * @return
     */
    public UserOrder findOneUserOrder(String orderId);


    /**
     * 后台管理员在查询订单的时候，订单中要包括用户名和用户订单
     */
    public List<UserOrder> findUserOrder(String username);

    /**
     * 在发货后或签收后修改订单
     * @param orderId
     * @param status
     */
    public int updateOrderStatus(Integer status,String orderId);
}
