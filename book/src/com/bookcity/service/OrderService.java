package com.bookcity.service;

import com.bookcity.entity.Cart;

public interface OrderService {
    /**
     *  创建订单
     * @param cart
     * @param userId
     */
    public String createOrder(Cart cart,Integer userId);
}
