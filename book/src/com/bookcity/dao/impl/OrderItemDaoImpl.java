package com.bookcity.dao.impl;

import com.bookcity.dao.OrderItemDao;
import com.bookcity.entity.OrderItem;

import java.util.List;

public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int save(OrderItem item) {
        String sql="insert into t_order_item(name,counts,price,totalPrice,orderId) values(?,?,?,?,?)";
        return  update(sql, item.getName(),item.getCounts(),item.getPrice(),item.getTotalPrice(),item.getOrderId());
    }

    @Override
    public List<OrderItem> findOrderItem(String orderId) {
        String sql="select * from t_order_item where orderId=?";
        return findList(OrderItem.class, sql, orderId);
    }
}
