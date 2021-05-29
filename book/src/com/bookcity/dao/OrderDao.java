package com.bookcity.dao;

import com.bookcity.entity.Order;

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
}
