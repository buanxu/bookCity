package com.bookcity.test;

import com.bookcity.dao.OrderDao;
import com.bookcity.dao.impl.OrderDaoImpl;
import com.bookcity.entity.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class OrderDaoTest {

    private OrderDao orderDao = new OrderDaoImpl();

    @Test
    public void save() {

        orderDao.save(new Order("234987654334",new Date(),new BigDecimal(108),0, 1));
    }

    @Test
    public void findOrder() {
        System.out.println(orderDao.findOrder(1));
    }
}