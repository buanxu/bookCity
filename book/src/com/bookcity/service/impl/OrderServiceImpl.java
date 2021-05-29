package com.bookcity.service.impl;

import com.bookcity.dao.BookDao;
import com.bookcity.dao.OrderDao;
import com.bookcity.dao.OrderItemDao;
import com.bookcity.dao.impl.BookDaoImpl;
import com.bookcity.dao.impl.OrderDaoImpl;
import com.bookcity.dao.impl.OrderItemDaoImpl;
import com.bookcity.entity.*;
import com.bookcity.service.OrderService;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao=new OrderDaoImpl();
    private OrderItemDao orderItemDao=new OrderItemDaoImpl();
    private BookDao bookDao=new BookDaoImpl();

    public String createOrder(Cart cart, Integer userId) {
        //保证订单的唯一性
        String orderId=System.currentTimeMillis()+""+userId;
        //创建订单
        Order order=new Order(orderId, new Date(), cart.getTotalPrice(), 0, userId);
        //保存订单
        orderDao.save(order);

//        int i=10/0;

        //遍历购物车，把其中的每一件商品都转换成订单项
        for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()){
            CartItem cartItem = entry.getValue();
            OrderItem orderItem=new OrderItem(cartItem.getId(),cartItem.getName(),
                    cartItem.getCounts(),cartItem.getPrice(),cartItem.getTotalPrice(),orderId);
            //保存订单项到数据库
            orderItemDao.save(orderItem);

            Book book = bookDao.findById(cartItem.getId());
            //每次创建订单，就更新相应商品的销量和库存
            book.setSales(book.getSales()+cartItem.getCounts());
            book.setStock(book.getStock()-cartItem.getCounts());
            bookDao.updateBook(book);
        }

        //结账创建完订单后要清空购物车
        cart.clearCart();

        return orderId;
    }

    @Override
    public List<Order> findOrder(Integer userId) {
        return orderDao.findOrder(userId);
    }

    @Override
    public List<OrderItem> findOrderItem(String orderId) {
        return orderItemDao.findOrderItem(orderId);
    }
}
