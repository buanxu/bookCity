package com.bookcity.dao;

import com.bookcity.entity.Book;
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


    /**
     * 查询订单状态
     * @param orderId
     * @return
     */
    public Integer findOrderStatus(String orderId);

    /**
     * 查询总的记录数
     * @return
     */
    public Integer findTotalRecords();

    /**
     * 分页查询
     * @param beginIndex 起始索引
     * @param pageSize  每页显示的记录数
     * @return
     */
    public List<UserOrder> findPageList(int beginIndex, int pageSize );
}
