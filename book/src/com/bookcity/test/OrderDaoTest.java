package com.bookcity.test;

import com.bookcity.dao.OrderDao;
import com.bookcity.dao.impl.OrderDaoImpl;
import com.bookcity.entity.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

public class OrderDaoTest {

    @Test
    public void save() {
        OrderDao orderDao = new OrderDaoImpl();

        orderDao.save(new Order("1234567891",new Date(),new BigDecimal(100),0, 1));
    }
}