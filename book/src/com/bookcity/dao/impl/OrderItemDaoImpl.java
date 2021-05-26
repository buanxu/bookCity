package com.bookcity.dao.impl;

import com.bookcity.dao.OrderItemDao;
import com.bookcity.entity.OrderItem;

public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int save(OrderItem item) {
        String sql="insert into t_order_item(name,counts,price,totalPrice,orderId) values(?,?,?,?,?)";
        return  update(sql, item.getName(),item.getCounts(),item.getPrice(),item.getTotalPrice(),item.getOrderId());
    }
}
