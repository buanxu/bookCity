package com.bookcity.dao;

import com.bookcity.entity.OrderItem;

public interface OrderItemDao {

    /**
     * 要添加订单项，则此订单项对应的订单id即orderId必须存在，所以添加了外键
     * @param item
     * @return
     */
    public int save(OrderItem item);
}
