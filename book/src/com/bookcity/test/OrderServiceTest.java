package com.bookcity.test;

import com.bookcity.entity.Cart;
import com.bookcity.entity.CartItem;
import com.bookcity.entity.Order;
import com.bookcity.entity.OrderItem;
import com.bookcity.service.OrderService;
import com.bookcity.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class OrderServiceTest {
    private OrderService orderService = new OrderServiceImpl();

    @Test
    public void createOrder() {
        Cart cart = new Cart();

        cart.addItem(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "数据结构与算法", 1, new BigDecimal(100),new BigDecimal(100)));


        System.out.println( "订单号是：" + orderService.createOrder(cart, 5) );
    }

    @Test
    public void findOrder() {
        System.out.println(orderService.findOrder(1));
    }

    @Test
    public void findOrderItem() {
        System.out.println(orderService.findOrderItem("1234567891"));
    }
}