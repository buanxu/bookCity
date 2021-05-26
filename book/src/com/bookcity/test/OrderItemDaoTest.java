package com.bookcity.test;

import com.bookcity.dao.OrderItemDao;
import com.bookcity.dao.impl.OrderItemDaoImpl;
import com.bookcity.entity.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderItemDaoTest {

    @Test
    public void save() {
        OrderItemDao orderItemDao = new OrderItemDaoImpl();

        orderItemDao.save(new OrderItem(null,"java从入门到精通", 1,new BigDecimal(100),new BigDecimal(100),"1234567891"));
        orderItemDao.save(new OrderItem(null,"javaScript从入门到精通", 2,new BigDecimal(100),new BigDecimal(200),"1234567891"));
        orderItemDao.save(new OrderItem(null,"Netty入门", 1,new BigDecimal(100),new BigDecimal(100),"1234567891"));
    }
}