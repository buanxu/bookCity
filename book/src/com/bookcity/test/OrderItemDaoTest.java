package com.bookcity.test;

import com.bookcity.dao.OrderItemDao;
import com.bookcity.dao.impl.OrderItemDaoImpl;
import com.bookcity.entity.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class OrderItemDaoTest {
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();

    /**
     * 由于filter加了事务，所以只有通过浏览器添加数据的时候才能成功保存在数据库里，在测试里没有触发事务，所以添加数据不会成功
     * 而且只有在添加了相应的订单后才能再添加订单项
     */
    @Test
    public void save() {
        orderItemDao.save(new OrderItem(null,"陆小凤之凤舞九天", 3,new BigDecimal(56),new BigDecimal(168),"267834920758347"));
        orderItemDao.save(new OrderItem(null,"碧血剑", 2,new BigDecimal(40),new BigDecimal(80),"267834920758347"));
        orderItemDao.save(new OrderItem(null,"瓜迪奥拉传", 1,new BigDecimal(69),new BigDecimal(69),"267834920758347"));
    }

    @Test
    public void findOrderItem() {
        System.out.println(orderItemDao.findOrderItem("1234567891"));
    }
}