package com.bookcity.dao;

import com.bookcity.entity.OrderItem;

import java.util.List;

public interface OrderItemDao {

    /**
     * 要添加订单项，则此订单项对应的订单id即orderId必须存在，所以添加了外键
     * @param item
     * @return
     */
    public int save(OrderItem item);

    /**
     * 查询订单的所偶有订单项，即该订单包含的所有的商品的信息
     * @param orderId
     * @return
     */
    public List<OrderItem> findOrderItem(String orderId);
}
