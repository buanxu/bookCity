package com.bookcity.dao.impl;

import com.bookcity.dao.OrderDao;
import com.bookcity.entity.Order;

public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public int save(Order order) {
        String sql="insert into t_order(orderId,createTime,price,status,userId) values(?,?,?,?,?)";
        return update(sql, order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }
}
