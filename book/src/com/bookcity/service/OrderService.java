package com.bookcity.service;

import com.bookcity.entity.Cart;
import com.bookcity.entity.Order;
import com.bookcity.entity.OrderItem;

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
}
