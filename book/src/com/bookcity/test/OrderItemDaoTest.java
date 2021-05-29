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

    @Test
    public void save() {

        orderItemDao.save(new OrderItem(null,"陆小凤之凤舞九天", 3,new BigDecimal(56),new BigDecimal(168),"234987654334"));
        orderItemDao.save(new OrderItem(null,"碧血剑", 2,new BigDecimal(40),new BigDecimal(80),"234987654334"));
        orderItemDao.save(new OrderItem(null,"瓜迪奥拉传", 1,new BigDecimal(69),new BigDecimal(69),"234987654334"));
    }

    @Test
    public void findOrderItem() {
        System.out.println(orderItemDao.findOrderItem("1234567891"));
    }
}