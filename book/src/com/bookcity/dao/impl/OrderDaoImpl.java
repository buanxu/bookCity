package com.bookcity.dao.impl;

import com.bookcity.dao.OrderDao;
import com.bookcity.entity.Book;
import com.bookcity.entity.Order;
import com.bookcity.entity.UserOrder;

import java.util.List;

public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public int save(Order order) {
        String sql="insert into t_order(orderId,createTime,price,status,userId) values(?,?,?,?,?)";
        return update(sql, order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }

    @Override
    public List<Order> findOrder(Integer userId) {
        String sql="select * from t_order where userId=?";
        return findList(Order.class, sql, userId);
    }

    @Override
    public UserOrder findOneUserOrder(String orderId) {
        String sql="SELECT uo.* FROM (SELECT u.id,u.username,o.* FROM  t_order o LEFT JOIN t_user u ON u.id=o.userId ) uo WHERE uo.orderId=?";
        return findOne(UserOrder.class, sql, orderId);
    }

    @Override
    public List<UserOrder> findUserOrder(String username) {
        if (username==null){//查询所有UserOrder
            String sql="SELECT id,username,o.* FROM  t_order o LEFT JOIN t_user u ON u.id=o.userId";
            return findList(UserOrder.class, sql);
        }else{//根据用户名查询UserOrder
            String sql2="SELECT uo.* FROM (SELECT u.id,u.username,o.* FROM  t_order o LEFT JOIN t_user u ON u.id=o.userId ) uo WHERE uo.username=?";
            return findList(UserOrder.class, sql2,username);
        }
    }

    @Override
    public int updateOrderStatus(Integer status,String orderId) {
        String sql="update t_order set status=? where orderId=?";
        return update(sql, status,orderId);
    }

    @Override
    public Integer findTotalRecords() {
        String sql="SELECT COUNT(orderId) FROM t_order";
        Number number=(Number) findSingleValue(sql);
        return number.intValue();
    }

    @Override
    public List<UserOrder> findPageList(int beginIndex, int pageSize) {
        String sql="SELECT uo.* FROM (SELECT u.id,u.username,o.* FROM  t_order o LEFT JOIN t_user u ON u.id=o.userId) uo LIMIT ?,?";
        List<UserOrder> userOrders = findList(UserOrder.class, sql, beginIndex, pageSize);
        return userOrders;
    }
}
